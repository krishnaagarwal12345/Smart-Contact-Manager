package com.SmartContactManager.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SmartContactManager.entities.User;
import com.SmartContactManager.repository.UserRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//fetching user from database 
		
		User user=repo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("no user found for this email");
		}
		CustomizeuserDetail customUser=new CustomizeuserDetail(user);
		
		return customUser;
	}
	
	
	
	
	

}
