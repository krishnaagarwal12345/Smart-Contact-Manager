package com.SmartContactManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SmartContactManager.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	@Query("SELECT u FROM User u where u.email= :email")
	public User findByEmail(@Param("email") String email);
	
	
}
