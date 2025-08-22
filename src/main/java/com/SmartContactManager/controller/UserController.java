package com.SmartContactManager.controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SmartContactManager.entities.Contact;
import com.SmartContactManager.entities.User;
import com.SmartContactManager.helper.message;
import com.SmartContactManager.repository.ContactRepository;
import com.SmartContactManager.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


//this is for the user information 
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repo;
	@Autowired
	private ContactRepository cepo;
	
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
	    if (principal != null) {
	        String email = principal.getName();
	        User user = repo.findByEmail(email);
	        model.addAttribute("user", user);
	    }
	}
	
	
	//http://localhost:8080/user/index
	//the Principal object represents the identity of the currently authenticated user. Its principal
	//use is to provide access to information about the logged-in user and to facilitate authorization decisions based on that identity.
	@GetMapping("/index")
	public String userDashboard(Model m,Principal prin) {
		String name=prin.getName();
		System.out.print(name);
		
		User user=repo.findByEmail(name);
		m.addAttribute("user",user);
		System.out.println(user);
		
		
		return "/normal/user_dashboard";
	}
	
	@GetMapping("/addcontact")
	public String addContact(Model m) {
		
		m.addAttribute("contact",new Contact());
		return "/normal/addContactForm";
	}
	//HTTP session used for giving the mssg on  the browser when the addcontact submitted successfully
	@PostMapping("/done")
	public String submitContact(@ModelAttribute Contact contact,@RequestParam("profileimage")MultipartFile file,Principal p 
			,HttpSession session) {
		
		//HTTP session used for giving the mssg on  the browser when the addcontact submitted successfully
		
			
			try {
				String name=p.getName();
				User user=this.repo.findByEmail(name);
				if(file.isEmpty()) {
					
					System.out.println("Empty");
					contact.setImage("default.png");
					user.setImage("default.png");
				}
			
			else{
			//saving the file to static/image folder
			contact.setImage(file.getOriginalFilename());
			File savefile=new ClassPathResource("/static/image").getFile();
			Path path=Paths.get(savefile.getAbsolutePath()+"/"+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			
			//save the contact is database
			user.getContact().add(contact); // add to user list
			contact.setUser(user);          // link contact to user
			this.repo.save(user);     // save user → saves contact too (cascade)
			System.out.println(contact);
			

			//showing message when the form is submitted
			
			session.setAttribute("message", new message("contact add successfully","success"));
			
			}
			catch(Exception e) {
				e.printStackTrace();
				session.setAttribute("message", new message("Something went Wrong","failure"));
			}
		    

		    // TODO: handle saving the image and setting contact.setImage()

			return "/normal/addContactForm";
		    
	    }
	@GetMapping("/showcontact/{page}")
	public String viewcontact(@PathVariable("page") Integer page,Principal p,Model m) { 
		String name=p.getName();
		User u1=this.repo.findByEmail(name);
//		List<Contact> contacts=u1.getContact();		
		Pageable pageable=PageRequest.of(page,5);
		Page<Contact> contacts=this.cepo.findByUserId(u1.getUserId(), pageable);
		m.addAttribute("contacts",contacts);
		m.addAttribute("currentpage",page);
	    m.addAttribute("totalpages",contacts.getTotalPages());
		
		return "normal/ViewContact";
	}
	
	//viewing a single specific contact
	@GetMapping("/contact/{cid}")
	public String specificContact(@PathVariable("cid") Integer cid, Model m) {
		
		Optional<Contact> contactoptional=this.cepo.findById(cid);
		Contact contact=contactoptional.get();
		m.addAttribute("c",contact);
		contact.setRole("User");
		this.cepo.save(contact);
		
		
		return "normal/ViewSpecificContact";
		
	}
	
	//contact profile handler
	
	@GetMapping("/profile")
	public String profilepage(Model m,Principal p) {
		String name=p.getName();
		User u=this.repo.findByEmail(name);
		m.addAttribute("u",u);
		return "normal/ContactProfile";
	}
    
	
	
	

}
