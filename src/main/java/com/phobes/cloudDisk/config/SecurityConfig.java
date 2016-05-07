package com.phobes.cloudDisk.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.phobes.cloudDisk.service.CurrentUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CurrentUserDetailsService userDetailsService;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .userDetailsService(userDetailsService);
	}	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
        .authorizeRequests()
        	.antMatchers(HttpMethod.POST).permitAll()
        	.antMatchers(HttpMethod.POST, "/upload**")
        		.access("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
        	.antMatchers("/hello**").permitAll()
        	.antMatchers("/hi**").permitAll()
        	.antMatchers(HttpMethod.POST, "/hi**").permitAll()
        	.antMatchers(HttpMethod.DELETE, "/user/**").permitAll()
        	.antMatchers(HttpMethod.POST, "/user**").permitAll()
        	.antMatchers(HttpMethod.GET,"/files/**").permitAll()
        	.antMatchers(HttpMethod.GET,"/filefold**")
        		.access("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
        	//.antMatchers(HttpMethod.GET,"/filefold/**").permitAll()
//        	.antMatchers(HttpMethod.GET, "/user**").
//        			access("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
//        	.antMatchers(HttpMethod.PUT,"/user/login**").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
        	.loginProcessingUrl("/user/login")
            .and();
		
		http.sessionManagement()
//			.disable();
			.sessionFixation().none()
			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
			;
	}	
	/*
	 * 
	 * 管理session
	 * 
	 * 
	 * 
    //是否需要转移Session中的值  
    private boolean migrateSessionAttributes = true;  
  
    //需要转移的Session中的值的列表  
    private List<String> retainedAttributes = null;  
  
    //是否创建新的Session  
    private boolean alwaysCreateSession;  
  
    //该方法是实现Session管理的主方法， 也就是SessionAuthenticationStrategy接口中的方法  
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {  
        boolean hadSessionAlready = request.getSession(false) != null;  
        //如果没有Session存在则返回不做处理  
        if (!hadSessionAlready && !alwaysCreateSession) {  
              
  
            return;  
        }  
  
        // 创建一个Session  
        HttpSession session = request.getSession();  
  
        if (hadSessionAlready && request.isRequestedSessionIdValid()) {  
            // We need to migrate to a new session  
            String originalSessionId = session.getId();  
  
            if (logger.isDebugEnabled()) {  
                logger.debug("Invalidating session with Id '" + originalSessionId +"' " + (migrateSessionAttributes ?  
                        "and" : "without") +  " migrating attributes.");  
            }  
  
            Map<String, Object> attributesToMigrate = extractAttributes(session);  
  
            session.invalidate();  
            session = request.getSession(true); // we now have a new session  
  
            if (logger.isDebugEnabled()) {  
                logger.debug("Started new session: " + session.getId());  
            }  
  
            if (originalSessionId.equals(session.getId())) {  
                logger.warn("Your servlet container did not change the session ID when a new session was created. You will" +  
                        " not be adequately protected against session-fixation attacks");  
            }  
            //转移Session中的属性值  
            transferAttributes(attributesToMigrate, session);  
<span style="white-space: pre;">    </span>    //Session转移后需要做的额外工作， 当前类中的方法为空方法，可由用户自主实现  
            onSessionChange(originalSessionId, session, authentication);  
        }  
    }
    */
	
}