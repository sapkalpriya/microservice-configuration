package com.lcwd.hotel.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.Repositories.HotelRepository;
import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;


@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		String hotelid = UUID.randomUUID().toString();
		hotel.setId(hotelid);
	    Hotel hotel1 = hotelRepository.save(hotel);
		return hotel1;
	}

	@Override
	public List<Hotel> getAll() {
		
		List<Hotel> hotels = hotelRepository.findAll();
		return hotels;
	}

	@Override
	public Hotel get(String id) {
		Hotel hotel1 = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given Id not found :"+id));
		return hotel1;
	}

}
