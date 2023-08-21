package com.lcwd.user.service.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.UserServiceImpl;

import ch.qos.logback.classic.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	//Create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
	//Single user
	
	int retryCount = 1;
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker" , fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		logger.info("Retry Count: {}",retryCount);
		retryCount++;
		User user =  userService.getUser(userId);

		 return ResponseEntity.ok(user); 
	}
	
	//creating fallback method for circuit breaker
	
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex)
	{
		logger.info("Fallback is executed because service is down :"+ ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.OK).body(new User("14527843","Dummy Name","dummy@gmail.com","This user is created dummy because some service is down",null));
	}
	

	//Get all user
	@GetMapping()
	public ResponseEntity<List<User>> getAllListOfUsers()
	{
		List<User> allusers =  userService.getAllUsers();
		return ResponseEntity.ok(allusers);
	}
	
	
	
	
	

}
