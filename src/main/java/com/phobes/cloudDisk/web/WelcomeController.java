package com.phobes.cloudDisk.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	
		@RequestMapping(value="/hello")
		public String hello(HttpSession session){
			session.setAttribute("user", "haha");
			System.out.println(session.getId());
			return "index";
		}
		
		@RequestMapping(value="/hi", method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.OK)  
		public String Hi(HttpSession session){
//			session.setAttribute("user", "haha");
			System.out.println(session.getId());
			System.out.println(session.getAttribute("user"));
			return "index";
		}



}