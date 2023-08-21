package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests 
{
	
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	
//	@Test
//	public void createRating()
//	{
//
//		Rating rate = new Rating(" "," ",8,"Good quality food");
//		
//		
//		ResponseEntity<Rating> createdrating  =  ratingService.createRating(rate);
//		
//		ResponseEntity.ok(ratingService.createRating(rate)).getStatusCode();
//		
//		createdrating.getStatusCode();
//		
//		System.out.println("New record is added to rating db");
//		
//		
//	}
	
	
	
}
