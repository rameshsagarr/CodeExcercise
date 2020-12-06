package com.mars.CodeExcercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.reflect.TypeToken;
import com.mars.CodeExcercise.controller.UserController;
import com.mars.CodeExcercise.exception.ResourceNotFoundException;
import com.mars.CodeExcercise.model.User;
import com.mars.CodeExcercise.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	private final String URL = "/api/v1/users/";

	@Test
	final void testCreateUser() throws Exception {
		User userObj = new User(1l, "ramesh", "sagar");
		when(userService.addUser(any(User.class))).thenReturn(userObj);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(userObj))).andReturn();

		result.getResponse().getStatus();
		assertEquals(result.getResponse().getStatus(), 200);
		verify(userService).addUser(any(User.class));
		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
		assertNotNull(resultUser);
	}

	@Test
	final void testGetAllUSers() throws Exception {
		User user = new User(2, "prakash", "reddy");
		User user2 = new User(3, "sundar", "reddy");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user2);
		when(userService.findAll()).thenReturn(userList);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		result.getResponse().getStatus();
		assertEquals(result.getResponse().getStatus(), 200);
		userService.findAll();
		TypeToken<List<User>> token = new TypeToken<List<User>>() {
		};
		List<User> userListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);
		assertNotNull(userListResult, "users not found");
		assertEquals(userList.size(), userListResult.size(), "Incorrect user List");

	}

	@Test
	final void testGetUsersCount() throws Exception {
		int count = 2;
		User user = new User(2, "prakash", "reddy");
		User user2 = new User(3, "sundar", "reddy");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user2);
		when(userService.findAll()).thenReturn(userList);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		result.getResponse().getStatus();
		userService.findAll().size();
		assertEquals(result.getResponse().getStatus(), 200);

	}

	@Test
	final void testUpdateUser() throws Exception {
		User user = new User(2, "prakash", "reddy");
		when(userService.findUserId(2l)).thenReturn(user);
		when(userService.updateUser(user)).thenReturn(user);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int statuscode = result.getResponse().getStatus();
		userService.updateUser(user);
		assertEquals(statuscode, 200);

	}
	
	@Test
	final void testDeleteUser() throws Exception {
		User user = new User(2, "prakash", "reddy");
		Map<String, Boolean> resultMap=new HashMap<String, Boolean>();
		resultMap.put("deleted", Boolean.TRUE);
		UserService userServiceObj=mock(UserService.class);
		doNothing().when(userServiceObj).deleteUser(2l);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int statuscode = result.getResponse().getStatus();
		userService.deleteUser(2l);
		assertEquals(statuscode, 200);

	}

	

}
