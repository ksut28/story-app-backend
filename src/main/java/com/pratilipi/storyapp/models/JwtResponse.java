package com.pratilipi.storyapp.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private Long userId;
	private String username;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JwtResponse(String jwttoken, Long userId, String username) {
		super();
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.username = username;
	}

}