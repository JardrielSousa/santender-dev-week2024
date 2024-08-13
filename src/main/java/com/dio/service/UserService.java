package com.dio.service;

import com.dio.model.User;

public interface UserService {
	User findById(Long id);
	
	User createUser(User userToCreate);
}
