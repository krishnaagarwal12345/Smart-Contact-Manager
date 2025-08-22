package com.SmartContactManager.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.SmartContactManager.entities.Contact;
import com.SmartContactManager.entities.User;
import com.SmartContactManager.repository.ContactRepository;
import com.SmartContactManager.repository.UserRepository;

@RestController
public class SearchController {
	@Autowired
	public UserRepository repo;
	@Autowired
	public ContactRepository cepo;
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal p){
		String name=p.getName();
		User u=this.repo.findByEmail(name);
		List<Contact> contacts=this.cepo.findByNameContainingAndUser(query, u);
		return ResponseEntity.ok(contacts);
		
		
		
		
		
		
	}
	

}
