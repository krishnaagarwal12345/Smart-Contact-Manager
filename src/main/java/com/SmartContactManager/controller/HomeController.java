package com.SmartContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SmartContactManager.entities.User;
import com.SmartContactManager.repository.UserRepository;

import jakarta.validation.Valid;



@Controller
public class HomeController {

	/* @Autowired */
	/*
	 * private UserRepository repo;
	 * 
	 * @GetMapping("/test")
	 */
    /*@ResponseBody
    public String test() {
        User user = new User();
        user.setName("krishna");
        user.setEmail("krishna@example.com");
        user.setPassword("password");
        user.setRole("USER");
        user.setEnabled(true);
        user.setAbout("testing");*/
       

        /*repo.save(user);
        System.out.println("HIT /test"); 
        return "Saved!";*/
		/* } */
	  //http://localhost:8080/home
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	
	  @RequestMapping("/home")
	  public String home() {
		  return "Home";
		  
	  }
	//http://localhost:8080/signup
	  @GetMapping("/signup")
	  public String Signup(Model m) {
		  m.addAttribute("user",new User());
		  return "SignUp";
		  
	  }
	  
	  @PostMapping("/register")
	  public String register(@Valid @ModelAttribute("user") User user,BindingResult result,@RequestParam(value="aggrement", defaultValue="false") boolean aggrement , 
			  Model m ) {
		  if(result.hasErrors()) {
			  System.out.println("Errors"+result.toString());
			  return "SignUp";
		  }
		  user.setEnabled(true);
		  user.setRole("ROLE_USER");
		  user.setImage("default.png");
		  user.setPassword(passEncoder.encode(user.getPassword()));
		  System.out.println("Aggrement:"+aggrement);
		  System.out.println(user);
		  
		  repo.save(user);
		 
		  return "SignUp";
		  
	  }
	  //login handler
	//http://localhost:8080/signup
	  @GetMapping("/signin")
	  public String login(Model m) {
		  m.addAttribute("user",new User());
		  return "login";
		  
	  }
	  
}







