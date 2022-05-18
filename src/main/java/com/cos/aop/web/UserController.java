package com.cos.aop.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.aop.domain.CommonDTO;
import com.cos.aop.domain.JoinReqDTO;
import com.cos.aop.domain.UpdateReqDTO;
import com.cos.aop.domain.User;
import com.cos.aop.domain.UserRepository;

@RestController
public class UserController {
	
	private final UserRepository userRepository;
	
	// DI
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// http://localhost:8080/user
	@GetMapping("/user")
	public CommonDTO<List<User>> findAll() {
		System.out.println("findAll()");
		
		return new CommonDTO<List<User>>(HttpStatus.OK.value(), userRepository.findAll()); // MessageConverter: Java 객체 → JSON
	}
	
	// http://localhost:8080/user/2
	@GetMapping("/user/{id}")
	public CommonDTO<User> findById(@PathVariable int id) {
		System.out.println("findById(): id=" + id);
		
		return new CommonDTO<User>(HttpStatus.OK.value(), userRepository.findById(id));
	}

	// http://localhost:8080/user
	// x-www-form-ulrencoded → request.getParameter()
	// text/plain → @RequestBody 
	// application/json → @RequestBody + 객체
	@CrossOrigin
	@PostMapping("/user")
	public CommonDTO<?> save(@Valid @RequestBody JoinReqDTO dto, BindingResult bindingResult /*String username, String password, String phone*/) {
		/*
		System.out.println("username=" + username);
		System.out.println("password=" + password);
		System.out.println("phone=" + phone);
		*/
		System.out.println("save()");	
		System.out.println("dto=" + dto);
		userRepository.save(dto);
		
		return new CommonDTO<>(HttpStatus.CREATED.value(), "OK");
	}

	// http://localhost:8080/user/2
	@DeleteMapping("/user/{id}")
	public CommonDTO<String> delete(@PathVariable int id) {
		System.out.println("delete()");
		userRepository.delete(id);
		
		return new CommonDTO<String>(HttpStatus.OK.value(), "OK");
	}

	// http://localhost:8080/user/2	
	@PutMapping("/user/{id}")
	public CommonDTO<?> update(@PathVariable int id, @Valid @RequestBody UpdateReqDTO dto, BindingResult bindingResult) {
		System.out.println("update()");
		userRepository.update(id, dto);
		
		return new CommonDTO<>(HttpStatus.OK.value(), "OK");
	}
}