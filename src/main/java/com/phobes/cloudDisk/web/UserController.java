package com.phobes.cloudDisk.web;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phobes.cloudDisk.domain.User;
import com.phobes.cloudDisk.service.CurrentUserDetailsService;
import com.phobes.cloudDisk.service.UserService;
@RestController
/*
 * 
 * @author phobes
**/
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CurrentUserDetailsService cuds;
	//获得所有用户
	@Autowired
    @RequestMapping(
            method=RequestMethod.GET)  
    @ResponseStatus(HttpStatus.OK)  
    public Iterable<User> getUsers() {  
        return this.userService.getUsers();  
    }
	//新建用户，注册用户
	@RequestMapping(
	         method=RequestMethod.POST)
	public @ResponseBody User
	addUser( @RequestParam(value="username") String username, @RequestParam(value="password") String password){
		//检查用户名是否存在
		if(this.userService.checkNameUsed(username) == false){
			 return this.userService.addUser(username, password);
		}
		return new User();
	}	
	//删除用户
	@RequestMapping(value="/{id}", 
	         method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)  
	public @ResponseBody void 
	deleteUser(@PathVariable long id){
		//检查用户名是否存在
		this.userService.deleteUser(id);
	}
	@RequestMapping(value="/{name}", 
	         method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)  
	public @ResponseBody User 
	findUser(@PathVariable String name){
		//检查用户名是否存在
		return this.userService.getUserByUsername(name);
	}
	//登录
    @RequestMapping(value="/login", 
            method=RequestMethod.POST)  
    public  @ResponseBody String login( @RequestParam(value="username") String username, @RequestParam(value="password") String password) {  
//        if (this.userService.signin(username, password)){
//        	return "登陆成功";
//        }
//        else{
//        	return "登陆失败, 用户名或密码不正确";
//        }
    	
    	cuds.loadUserByUsername(username);
    	return "";
    } 

}
