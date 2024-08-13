package com.dio.serviceImpl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.dio.model.User;
import com.dio.repository.UserRepository;
import com.dio.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User createUser(User userToCreate) {
		if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This account number already exists.");
		}
		return userRepository.save(userToCreate);
	}

}
