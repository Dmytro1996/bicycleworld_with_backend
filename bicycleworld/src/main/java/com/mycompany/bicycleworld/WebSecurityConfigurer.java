/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bicycleworld;

import com.mycompany.bicycleworld.model.AuthProvider;
import com.mycompany.bicycleworld.model.User;
import com.mycompany.bicycleworld.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
/**
 *
 * @author dmytr
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter  {
    
    @Autowired
    private UserService userService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{       
        http.csrf().disable().authorizeRequests().antMatchers("/users/create","/page/**","/css/**",
                "/javascript/**","/images/**","/login","/index","/").permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/").loginProcessingUrl("/login")
                .defaultSuccessUrl("/index").failureUrl("/error?userNotFound=true")
                .permitAll().and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/index").deleteCookies("JSESSIONID").and().oauth2Login()
                .successHandler(new AuthenticationSuccessHandler(){
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest hsr, 
                            HttpServletResponse hsr1, Authentication a) 
                            throws IOException, ServletException {
                        OAuth2User oauthUser=(OAuth2User)a.getPrincipal();
                        String email=oauthUser.getAttribute("email");                        
                        if(userService.getAll().stream().filter(u->u.getEmail().equals(email)).count()==0){
                            User user=new User();
                            user.setFullName(oauthUser.getAttributes().get("name").toString());
                            user.setEmail(email);
                            user.setPassword("default_pass");
                            user.setAuthProvider(AuthProvider.GOOGLE);
                            userService.create(user);
                        }
                        hsr1.sendRedirect("/index");
                    }
                
                });       
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider provider) throws Exception{        
        auth.authenticationProvider(provider);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
