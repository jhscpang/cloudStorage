package com.phobes.cloudDisk.service;


import com.phobes.cloudDisk.domain.User;

public interface UserService {
	Iterable<User> getUsers();
	boolean signin(String name, String password);
	boolean register(User user);
	boolean checkNameUsed(String username);
	User getUserByUsername(String username);  
	User addUser(String username, String password);
	boolean deleteUser(long id);
}
