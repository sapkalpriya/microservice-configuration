package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entities.Rating;

@Service
@FeignClient("RATING-SERVICE")
public interface RatingService 
{
	
	//get 
	
	
	//Post 
	
	@PostMapping("/ratings")
	ResponseEntity<Rating> createRating(Rating value);
	
	
	//Update
	
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable String ratingId  ,Rating rating);
	
	
	//Delete
	
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId  ,Rating rating);
	
	

}
