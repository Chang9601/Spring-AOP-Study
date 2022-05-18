package com.cos.aop.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// Exception 낚아채기
@RestController
@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String badReq(IllegalArgumentException e) {
		return e.getMessage();
	}
}