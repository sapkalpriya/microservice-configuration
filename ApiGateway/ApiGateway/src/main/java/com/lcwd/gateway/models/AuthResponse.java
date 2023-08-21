package com.lcwd.gateway.models;

import java.util.Collection;



public class AuthResponse {

	private String userId;
	
	private String accessToken;
	
	private String refreshToken;
	
	private long expireAt;
	
	private Collection<String> authories;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(long expireAt) {
		this.expireAt = expireAt;
	}

	public Collection<String> getAuthories() {
		return authories;
	}

	public void setAuthories(Collection<String> authories) {
		this.authories = authories;
	}

	
	public AuthResponse(String userId, String accessToken, String refreshToken, long expireAt,
			Collection<String> authories) {
		super();
		this.userId = userId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expireAt = expireAt;
		this.authories = authories;
	}

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AuthResponse [userId=" + userId + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken
				+ ", expireAt=" + expireAt + ", authories=" + authories + "]";
	}
	
	
	
	
}
