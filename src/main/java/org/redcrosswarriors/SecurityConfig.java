package org.redcrosswarriors;

import org.redcrosswarriors.security.AccessDeniedHandlerImp;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // allow security on methods
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountDetailsService userDetailsService;

    @Override
    // defines which urls should be secured 
	protected void configure(HttpSecurity http) throws Exception {

    	// specify that the user can login using form data, where login is, where to go when it fails, and when it's successful
		http.formLogin().loginPage("/")
				.loginProcessingUrl("/")
				.failureUrl("/?error=true")
				.defaultSuccessUrl("/", true)
				.and()
				.logout()
				.logoutSuccessUrl("/?logout=true");

		http.csrf().disable(); // allow us to make requests logged in from different domains

		// session rules
		// specify that you can only be logged in on one computer at a time.
		// if you log in on two computers at once then you will be logged out of the other one.
		http.sessionManagement().maximumSessions(1);

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
    	return new AccessDeniedHandlerImp();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}