package com.lcwd.rating.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController
{

	@Autowired
	RatingService ratingService;
	
	
	//create
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating)
	{
		Rating ratingcreated =  ratingService.create(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingcreated);
	}
	
	
	//Get ratings by userId
	
	@PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId)
	{
		List<Rating> ratingByUserId = ratingService.getByUserId(userId);
		return ResponseEntity.ok(ratingByUserId);
	}
	
	
	//get Ratings by hotelId
	
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId)
	{
		List<Rating> ratingByHotelId = ratingService.getByHotelId(hotelId);
		return ResponseEntity.ok(ratingByHotelId);
	}
	
	
	//get all rating
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings()
	{
		List<Rating> listRatings =  ratingService.getAll();
		return ResponseEntity.ok(listRatings);
	}
	
	
	
	
	
	
}
