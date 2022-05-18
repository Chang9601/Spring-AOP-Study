package com.cos.aop.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class JoinReqDTO {
	
	@NotBlank(message = "아이디 필수")
	@Size(max = 20, message = "아이디 길이 초과")
	private String username;

	@NotBlank(message = "비밀번호 필수")
	private String password;
	
	private String phone;
	
	@Builder
	public JoinReqDTO(String username, String password, String phone) {
		this.username = username;
		this.password = password;
		this.phone = phone;
	}
}