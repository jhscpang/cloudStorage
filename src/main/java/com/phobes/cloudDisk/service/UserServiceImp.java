package com.phobes.cloudDisk.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phobes.cloudDisk.domain.Role;
import com.phobes.cloudDisk.domain.User;
@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public Iterable<User> getUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}
	@Override
	public boolean signin(String username, String password) {
		// TODO Auto-generated method stub
		User  user;
		try {
			user = this.userRepository.findByUsername(username);
		}
		catch (Exception ex){
			return false;
		}
		if(	 user==null || !password.equals(user.getPassword())){
			return false;
		}
		else {
			return true;
		}
		
	}
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public User addUser(String username, String password){
		User user = new User();
		String telephone  = "13810414691";
		user.setUsername(username);
		user.setPassword(password);
		user.setIlleage_num(0);
		user.setRole(Role.ROLE_USER);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    java.sql.Date regist_time = java.sql.Date.valueOf( df.format(new java.util.Date()) );
		user.setRegist_time(regist_time);
		user.setTelephone(telephone);
		user.setTotal_space(500);
		user.setUsed_space(0);
		user.setState(0);
		return this.userRepository.save(user);
	}
	@Override
	//检查用户名是否被使用
	//用户名存在，返回true, 否则返回false
	public boolean checkNameUsed (String username){
		User user = this.userRepository.findByUsername(username);
		if (user != null) {
			return true;
		}
		else {
			return false;
		}
	}
	//删除对应id的用户
	@Override
	public boolean deleteUser (long id){
		//是否id对应的用户存在
		User user = this.userRepository.findOne(id);
		if (user != null) {
			this.userRepository.delete(id);
		    return true;
		}
		else {
			return false;
		}
	}
	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}
}
