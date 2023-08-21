package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {
	
	//create
	Rating create(Rating rating);
	
	
	//get  rating by userId
	List<Rating> getByUserId(String userId);
	
	
	
	//get rating by hotelId
	List<Rating> getByHotelId(String hotelId);
	
	
	
	//get all rating
	List<Rating> getAll();
	
	

}
