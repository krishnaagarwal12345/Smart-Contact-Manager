package com.SmartContactManager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SmartContactManager.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
//	public List<Contact> findByUserId(@Param("userid") int userid);
	@Query("FROM Contact as c where  c.user.userId=:userid")
	public Page<Contact> findByUserId(@Param("userid") int userid,Pageable pageable);
	
	
	

}
