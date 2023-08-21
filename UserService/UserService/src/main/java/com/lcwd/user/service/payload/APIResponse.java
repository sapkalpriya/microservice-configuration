package com.lcwd.user.service.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse
{
	
	private String message;
	private boolean success;
	private HttpStatus status;
	
	public APIResponse(String message)
	{
		this.message = message;
	}
	

}
