package com.cos.aop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CommonDTO<T> {
	private int status;
	private T data;
	
	public CommonDTO(int status, T data) {
		super();
		this.status = status;
		this.data = data;
	}
}