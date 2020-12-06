package com.mars.CodeExcercise.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mars.CodeExcercise.exception.ResourceNotFoundException;
import com.mars.CodeExcercise.model.User;

public interface UserService {

	User addUser(User user);

	List<User> findAll();

	User findUserId(Long userId) throws ResourceNotFoundException;

	User updateUser(User user);

	void deleteUser(Long id) throws ResourceNotFoundException;

}
