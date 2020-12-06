package com.mars.CodeExcercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.CodeExcercise.exception.ResourceNotFoundException;
import com.mars.CodeExcercise.exception.UserNotFoundException;
import com.mars.CodeExcercise.model.User;
import com.mars.CodeExcercise.repository.UserRepository;
import com.mars.CodeExcercise.service.UserService;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> usersList = userService.findAll();
		return usersList;

	}

	@GetMapping("/userscount")
	public int getUsersCount() {
		List<User> usersList = userService.findAll();
		int userscount = usersList.size();
		return userscount;

	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		User createdUser = userService.addUser(user);
		return createdUser;

	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws Exception {
		User user = userService.findUserId(userId);
		if (user.getId() < 0) {
			throw new UserNotFoundException("userid" + userId);
		}
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		userService.deleteUser(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
