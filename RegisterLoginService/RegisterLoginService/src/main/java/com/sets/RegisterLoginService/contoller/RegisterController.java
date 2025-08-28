package com.sets.RegisterLoginService.contoller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sets.RegisterLoginService.model.LoginDTO;
import com.sets.RegisterLoginService.model.RegisterDTO;
import com.sets.RegisterLoginService.security.JwtTokenHolder;
import com.sets.RegisterLoginService.security.JwtUtil;
import com.sets.RegisterLoginService.service.DashboardService;
import com.sets.RegisterLoginService.service.RegisterService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login-register")
public class RegisterController {

	@Autowired
    AuthenticationManager authenticationManager;                      
	
	@Autowired
	JwtUtil jwtUtils;
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("userDetails", new RegisterDTO());
		return "register";
	}
	
	@GetMapping("/register")
	public String register(Model model)
	{
		System.out.println("FLASH MESSAGE: " + model.asMap().get("successMsg"));
		model.addAttribute("userDetails", new RegisterDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("userDetails") RegisterDTO registerDTO,
	                           BindingResult bindingResult,
	                           RedirectAttributes redirectAttributes) {
	    if (bindingResult.hasErrors()) {
	        return "register"; 
	    }

	    String msg = registerService.registerUser(registerDTO);
	    System.out.println("msg  - > " + msg);
	    redirectAttributes.addFlashAttribute("successMsg", msg);
	    
	    if ("user already exist".equalsIgnoreCase(msg)) {
	        redirectAttributes.addFlashAttribute("msgColor", "danger");
	    } else {
	        redirectAttributes.addFlashAttribute("msgColor", "success");
	    }

	    return "redirect:/login-register/register"; 
	}
	
	@GetMapping("/login")
	public String getloginpage()
	{
		System.out.println("inside login page-----------");
		return "login";
	}
	
	@PostMapping("/login")
    public String authenticateUser(@ModelAttribute("loginDetails") LoginDTO loginDto,
    								HttpServletResponse response,Model model) {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		loginDto.getEmail(),
	                		loginDto.getPassword()
	                )
	        ); 
	        
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			try {
				String token = jwtUtils.generateToken(userDetails.getUsername());
				
				JwtTokenHolder.setToken(token);
				Cookie cookie = new Cookie("jwt", token);
				cookie.setHttpOnly(true);
				cookie.setSecure(false);
				cookie.setPath("/");
				response.addCookie(cookie);
				System.out.println("token is :  " + token);
				LocalDate todate = LocalDate.now();
				LocalDate fromdate = todate.withDayOfMonth(1);
				dashboardService.showLandingPageDetails(model,userDetails.getUsername(),fromdate,todate);
				JwtTokenHolder.clear();
				return "dashboard";

			}
	        catch(Exception ex) 
	        {
	        	ex.printStackTrace();
	        	return "login";
	        }
    }

	@GetMapping("/dashboard")
	public String ShowDashboard(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            					@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            					Model model,HttpServletRequest request) {
		System.out.println("inside show dashboard");
		System.out.println("fromDate : "+fromDate+"toDate : "+toDate);
		   String username = null;
		    if (request.getCookies() != null) {
		        for (Cookie cookie : request.getCookies()) {
		            if ("jwt".equals(cookie.getName())) {
		                username = jwtUtils.getUsernameFromToken(cookie.getValue());
		                break;
		            }
		        }
		    }
		    if(fromDate==null && toDate==null) {
		    	LocalDate todate = LocalDate.now();
				LocalDate fromdate = todate.withDayOfMonth(1);
				dashboardService.showLandingPageDetails(model,username,fromdate,todate);
				 return "dashboard";
		    }
		dashboardService.showLandingPageDetails(model,username,fromDate,toDate);

	    return "dashboard";
	}
	
	@GetMapping("/logout")
	public String Logout(HttpServletResponse response)
	{
		Cookie cookie = new Cookie("jwt", null);
		cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setMaxAge(0); // delete cookie
	    response.addCookie(cookie);

	    return "redirect:/login-register/login";
	}
	
}
