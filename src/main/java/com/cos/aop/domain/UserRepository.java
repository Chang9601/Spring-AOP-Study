package com.cos.aop.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		
		users.add(new User(1, "ssar", "1234", "0102222"));
		users.add(new User(2, "cos", "1234", "0102222"));
		users.add(new User(3, "love", "1234", "0102222"));
		
		return users;
	}
	
	public User findById(int id) {
		return new User(1, "ssar", "1234", "0102222");
	}
	
	public void save(JoinReqDTO dto) {
		System.out.println("DB에 삽입");
	}
	
	public void delete(int id) {
		System.out.println("DB에서 삭제");
	}
	
	public void update(int id, UpdateReqDTO dto) {
		throw new IllegalArgumentException("잘못된 인자");
		//System.out.println("DB에서 수정");
	}
}