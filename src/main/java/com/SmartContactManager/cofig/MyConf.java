package com.SmartContactManager.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConf {
	
	@Bean
	public UserDetailsService getUserDetailservice(){
		
		return new UserDetailServiceImpl();
		
	}
	@Bean
    public BCryptPasswordEncoder passEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider AuthenticationProvider() {
		DaoAuthenticationProvider daoAuthentication=new DaoAuthenticationProvider();
		daoAuthentication.setUserDetailsService(getUserDetailservice());
		daoAuthentication.setPasswordEncoder(passEncoder());
		return daoAuthentication;
	}
	
	protected void cofigure(AuthenticationManagerBuilder auth) throws Exception{
		 auth.authenticationProvider(AuthenticationProvider());
	}
	@Bean
	protected SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception{
		http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("USER")
            .requestMatchers("/**").permitAll()
        )
        .formLogin().loginPage("/signin")
        .loginProcessingUrl("/Loginsubmit")   //it submits the username and password
        .defaultSuccessUrl("/user/index")      //after successfull login it directed to the given url
        .failureUrl("/fail-login")             // after failure of login it redirected to the given url
        .and()
        .csrf().disable();
		
		
	
	return http.build();

   }
}
