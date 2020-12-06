package com.mars.CodeExcercise.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.CodeExcercise.exception.ResourceNotFoundException;
import com.mars.CodeExcercise.model.User;
import com.mars.CodeExcercise.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {

		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public List<User> findAll() {
		List<User> usersList = userRepository.findAll();
		return usersList;
	}

	@Override
	public User findUserId(Long userId) throws ResourceNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		// return userRepository.findById(userId);
	}

	@Override
	public User updateUser(User user) {
		User userResult = userRepository.save(user);
		return userResult;
	}

	@Override
	public void deleteUser(Long id) throws ResourceNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + id));
		userRepository.delete(user);

	}

}
