package com.phobes.cloudDisk.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.phobes.cloudDisk.Application;
import com.phobes.cloudDisk.domain.CurrentUser;

public class Path {
	public String getCurrentBasePath(HttpServletRequest request ){
		return  getApplicationRoot(request)+Application.ROOT+ "/"
				+ this.getAuthenticatedUsername()+"/";
	}  
	private String getApplicationRoot(HttpServletRequest request){
//		return  request.getSession().getServletContext().getRealPath("/");
		return "";
	}
	private  String getAuthenticatedUsername() { 
	    String username = null; 
	    Object principal = SecurityContextHolder.getContext() 
	        .getAuthentication().getPrincipal(); 
	    if (principal instanceof CurrentUser) { 
	        username = ((CurrentUser) principal).getUsername(); 
	    } else { 
	        username = principal.toString(); 
	    } 
	    return username; 
	 } 
}
