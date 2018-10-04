package com.gpware.billing;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.gpware.billing.helper.ApplicationProperties;
import com.gpware.billing.helper.UserPropertyLoader;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ApplicationProperties appProp;
	
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		String[] userList = appProp.getUserNames().split(appProp.getUserPwdDelimiter());
		
		Map<String, String> userNamePwdMap = UserPropertyLoader.loadUserPropertyMap(appProp.getUserPropsFilePath(), userList);
		for (Map.Entry<String, String> entry : userNamePwdMap.entrySet()) {
	        System.out.println(entry.getKey() + ":" + entry.getValue());
	        
	        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
			.withUser(entry.getKey()).password(entry.getValue()).roles("USER");
	    }
		
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
			.withUser(appProp.getAdminUserName()).password(appProp.getAdminUserPwd()).roles("USER", "ADMIN");
		
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic().and().authorizeRequests()
			.antMatchers("/error.jsp").permitAll()
			.antMatchers("/assets/img/**").permitAll()
			.antMatchers("/").hasRole("USER")
			.antMatchers("/favicon.ico").hasRole("USER")
			.antMatchers("/assets/**").hasRole("USER")
			.antMatchers("/home/**").hasRole("USER")
			.antMatchers("/billing/**").hasRole("USER")
			.antMatchers("/customer/**").hasRole("USER")
			.antMatchers("/product/**").hasRole("USER")
			.antMatchers("/customers/**").hasRole("USER")
			.antMatchers("/products/**").hasRole("USER")
			.antMatchers("/reports/**").hasRole("USER")
			.antMatchers("/print/**").hasRole("USER")
			.antMatchers("/**").hasRole("ADMIN").and()
			.csrf().disable().headers().frameOptions().disable();
	}

}
