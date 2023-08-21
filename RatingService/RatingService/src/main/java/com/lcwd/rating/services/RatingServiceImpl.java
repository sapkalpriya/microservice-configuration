package com.lcwd.rating.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositories.RatingRepository;


@Service
public class RatingServiceImpl implements RatingService
{
	
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) 
	{
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		Rating rating1 = ratingRepository.save(rating);
		return rating1;
	}

	@Override
	public List<Rating> getByUserId(String userId) 
	{
	   List<Rating> listByUserId =  ratingRepository.findByUserId(userId);
		return listByUserId;
	}
	

	@Override
	public List<Rating> getByHotelId(String hotelId) {
		List<Rating> listByHotelId =  ratingRepository.findByHotelId(hotelId);
		return listByHotelId;
	}

	@Override
	public List<Rating> getAll() {
		List<Rating> ratings =  ratingRepository.findAll();
		return ratings;
	}

}
