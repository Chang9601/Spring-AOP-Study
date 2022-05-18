package com.cos.aop.domain;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UpdateReqDTO {
	
	@NotBlank(message = "비밀번호 필수")
	private String password;
	
	
	private String phone;
	
	@Builder
	public UpdateReqDTO(String password, String phone) {
		this.password = password;
		this.phone = phone;
	}
}