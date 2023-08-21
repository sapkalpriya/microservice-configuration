package com.lcwd.user.service.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//While using Feign Interface
	@Autowired
	private HotelService hotelService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User saveUser(User user) 	
	{
		//generate unique userId
		String randomUid = UUID.randomUUID().toString();
		user.setUserId(randomUid);
		User user1 = userRepository.save(user);
		return user1;
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		//Can Implement RATING-SERVICE call using : RESTTEMPLATE
		
		return users;
	}

	//Get single user 
	
	@Override
	public User getUser(String userId) {
		//Get user with the help of UserRepository to connect and get data from db
		User user1 =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server"+userId));
	    
		//Fetch rating of above userId from RATING-SERVICE
		Rating[] listOfRating = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user1.getUserId(), Rating[].class);
		
		
		
		List<Rating> ratings = Arrays.stream(listOfRating).toList();
		logger.info("{}", ratings);
		
		List<Rating> listOfHotelRating = ratings.stream().map(rating->{
			//api call to hotel service to get the hotel details
			//Hotel hotel1 = restTemplate.getForObject("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
			
			//When you use Feign Client to call API other microservice
			Hotel hotel1 = hotelService.getHotel(rating.getHotelId());
			
			//logger
			//logger.info("{}", hotel1);
			
			//set the value in Rating class "setHotel()"
			rating.setHotel(hotel1);
			
			return rating;
			
		}).collect(Collectors.toList());
		
		logger.info("{}", listOfHotelRating);
		
		user1.setRatings(listOfHotelRating);
		
		
		return user1;
	}

}
