package com.lcwd.hotel.controllers;

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

import com.lcwd.hotel.Services.HotelService;
import com.lcwd.hotel.entities.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController
{

	@Autowired
	HotelService hotelService;
	
	//Create 
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping 
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel hotel1 = hotelService.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
	}
	
	
	//get all hotel
	
	@PreAuthorize("hasAuthority('Admin') || hasAuthority('SCOPE_internal')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getAll()
	{
		 List<Hotel> hotels = hotelService.getAll();
		 return ResponseEntity.ok(hotels);
	}
	
	
	
	//get single hotel
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String id)
	{
		Hotel hotel1 = hotelService.get(id);
		return ResponseEntity.status(HttpStatus.OK).body(hotel1);
	}
	{
		
	}
	
	
	
	
}
