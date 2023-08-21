package com.lcwd.gateway.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.gateway.models.AuthResponse;


@RestController
@RequestMapping("/auth")
public class AuthController
{
	private org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	
	//From client - we will get access token , expires at 
	//From user - emmail id password basically we will get authorities
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			)
	{
		
		logger.info("User email id {}",user.getEmail());
		
		//Creating Auth Response object
		
		AuthResponse authResponse = new AuthResponse();
		
		
		//Setting authResponse Email
		authResponse.setUserId(user.getEmail());
		
		//Setting accessToken to authResponse 
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		
		
		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
		
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		List<String>  authorities =  user.getAuthorities().stream().map(grantedAuthority->{
			return grantedAuthority.getAuthority();
			}).collect(Collectors.toList());
		
		authResponse.setAuthories(authorities);
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
		
		
	}
	

}
