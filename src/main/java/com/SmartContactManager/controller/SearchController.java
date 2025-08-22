package com.SmartContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.SmartContactManager.repository.UserRepository;

@RestController
public class SearchController {
	@Autowired
	public UserRepository repo;

}
