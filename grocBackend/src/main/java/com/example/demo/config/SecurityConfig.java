package com.example.demo.config;

import com.example.demo.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/product/**",
            "/user/**"
    };

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { //
	 * http.cors().and().httpBasic().and().authorizeRequests().antMatchers(
	 * PUBLIC_MATCHERS).permitAll().anyRequest().authenticated(); //
	 * http.csrf().disable(); //
	 * 
	 * http.cors().and(). httpBasic() .and() .authorizeRequests()
	 * .antMatchers("/token") .permitAll() .anyRequest() .authenticated();
	 * http.csrf().disable();
	 * 
	 * 
	 * 
	 * }
	 */
    
    @Override
    protected void configure(HttpSecurity http) 
      throws Exception {
		/*
		 * http.csrf().disable() .authorizeRequests() .antMatchers("/").permitAll()
		 * .anyRequest() .authenticated() .and() .httpBasic();
		 */
    	http.csrf().disable();
        /*.authorizeRequests()
        .antMatchers("/token").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();*/
    }
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.cors().configurationSource(request -> new
	 * CorsConfiguration().applyPermitDefaultValues()); }
	 */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println(auth.userDetailsService(userSecurityService).getUserDetailsService());
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public HeaderHttpSessionIdResolver httpSessionStrategy() {
        return HeaderHttpSessionIdResolver.authenticationInfo();
    }
}
