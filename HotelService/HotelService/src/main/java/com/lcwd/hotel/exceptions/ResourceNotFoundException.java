package com.lcwd.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
	
	public ResourceNotFoundException()
	{
		super("Resource Not found on server");
	}
	
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}

}
