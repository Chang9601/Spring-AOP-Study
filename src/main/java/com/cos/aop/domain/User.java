package com.cos.aop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class User {
	private int id;
	private String username;
	private String password;
	private String phone;
	
	@Builder
	public User(int id, String username, String password, String phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
	}
}