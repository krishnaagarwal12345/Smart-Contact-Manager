package com.SmartContactManager.cofig;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SmartContactManager.entities.User;

public class CustomizeuserDetail implements UserDetails {
	

	
	private User u;
    
	public CustomizeuserDetail(User u) {
		super();
		this.u = u;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simple=new SimpleGrantedAuthority(u.getRole()) ;
		
		return List.of(simple);
		
	}

	@Override
	public String getPassword() {
		
		return u.getPassword();
	}

	@Override
	public String getUsername() {
		
		return u.getEmail();
	}
	@Override
    public boolean isAccountNonExpired() {
        return true; // you can add logic here if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // or u.isLocked() if you have such a field
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return u.isEnabled();
    }
	

}
