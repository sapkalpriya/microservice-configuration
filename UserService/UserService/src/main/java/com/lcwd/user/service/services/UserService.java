package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService
{
	
	//User Operation 
	
	
	//CREATE 
	User saveUser(User user);
	
	
	//Get All User
	List<User> getAllUsers();
	
	
	//Get single User
	User getUser(String userId);
	
	
	//
	

}
