package com.cos.aop.config;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cos.aop.domain.CommonDTO;

import io.sentry.Sentry;

@Component
@Aspect
public class BindingAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(BindingAdvice.class);
	
	// 어느 함수가 언제 몇 번 실행되었는지 횟수 로그 남기기
	@Before("execution(* com.cos.aop.web..*Controller.*(..))")
	public void testBefore() {
		// request 처리
		HttpServletRequest req = 
		 	((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		System.out.println("주소: " + req.getRequestURI());
		
		// log 처리
		System.out.println("전로그");
	}
	
	@After("execution(* com.cos.aop.web..*Controller.*(..))")
	public void testAfter() {
		System.out.println("후로그");
	}

	// 함수: 앞 뒤 → @Around
	// 함수: 앞 → @Before
	// 함수: 뒤 → @After
	@Around("execution(* com.cos.aop.web..*Controller.*(..))")
	public Object validate(ProceedingJoinPoint point) throws Throwable {
		String type = point.getSignature().getDeclaringTypeName();
		String method = point.getSignature().getName();

		System.out.println("type: " + type);
		System.out.println("method: " + method);

		Object[] args = point.getArgs();

		for (var arg : args) {
			if (arg instanceof BindingResult) {
				var bindingResult = (BindingResult) arg;

				// 서비스: 정상적인 화면 → 사용자 요청
				if (bindingResult.hasErrors()) {
					var errorMap = new HashMap<String, String>();
					for (var error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						
						// 로그 레벨
						// 1. error
						// 2. warn
						// 3. info
						// 4. debug
						
						logger.warn(type + "." + method + "() → 필드: " + error.getField() + ", 메시지: " + error.getDefaultMessage());
						logger.debug(type + "." + method + "() → 필드: " + error.getField() + ", 메시지: " + error.getDefaultMessage());
						Sentry.captureMessage(type + "." + method + "() → 필드: " + error.getField() + ", 메시지: " + error.getDefaultMessage());
					}
					
					return new CommonDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
				}
			}				
		}

		return point.proceed(); // 함수의 스택 실행
	}
}