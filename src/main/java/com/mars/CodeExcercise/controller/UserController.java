package com.mars.CodeExcercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mars.CodeExcercise.model.User;
import com.mars.CodeExcercise.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> usersList=userRepository.findAll();
		return usersList;
		
	}
	
	@GetMapping("/userscount")
	public int getUsersCount(){
		List<User> usersList=userRepository.findAll();
		int userscount=usersList.size();
		return userscount;
		
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user)
	{
		System.out.println(user);
		User createdUser=userRepository.save(user);
		return createdUser;
		
	}
	
	 @PutMapping("/user/{id}")
	    public ResponseEntity < User > updateUser(@PathVariable(value = "id") Long userId,
	        @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
	        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
	        user.setLastName(userDetails.getLastName());
	        user.setFirstName(userDetails.getFirstName());
	        final User updatedUser = userRepository.save(user);
	        return ResponseEntity.ok(updatedUser);
	    }
	 
	 @DeleteMapping("/user/{id}")
	    public Map < String, Boolean > deleteUser(@PathVariable(value = "id") Long id)
	    throws ResourceNotFoundException {
	        User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + id));

	        userRepository.delete(user);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }

}
