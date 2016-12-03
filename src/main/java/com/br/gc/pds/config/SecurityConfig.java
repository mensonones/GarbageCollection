package com.br.gc.pds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
	
		   http.authorizeRequests()
		   .antMatchers("/home").access("hasRole('ADMIN')")  
		   .anyRequest().permitAll()
		   .and()
		     .formLogin().loginPage("/login")
		     .usernameParameter("login").passwordParameter("password")
		     .defaultSuccessUrl("/home")
		   .and()
		   .logout()
           .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/login")
		     //.logout()
		    // .logoutUrl("/logout")
		    // .logoutSuccessUrl("/login") 
		    .and()
		    .exceptionHandling().accessDeniedPage("/login-error")
		   .and()
		     .csrf();

	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {  
    	auth.inMemoryAuthentication().withUser("user").password("user").roles("ADMIN");
}
}
