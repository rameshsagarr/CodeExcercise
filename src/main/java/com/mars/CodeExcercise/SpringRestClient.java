package com.mars.CodeExcercise;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.mars.CodeExcercise.model.User;

public class SpringRestClient {

	private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:9090/api/v1/users";
	private static final String UPDATE_USER_ENDPOINT_URL = "http://localhost:9090/api/v1/user/{id}";
	private static final String DELETE_USER_ENDPOINT_URL = "http://localhost:9090/api/v1/user/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient restclient = new SpringRestClient();
		restclient.createUser();
		restclient.updateUser();
		restclient.deleteUSer();

	}

	private void deleteUSer() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		restTemplate.delete(DELETE_USER_ENDPOINT_URL, params);

	}

	private void updateUser() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		User updatedUser = new User(1, "prakash", "kumar");
		restTemplate.put(UPDATE_USER_ENDPOINT_URL, updatedUser, params);

	}

	private void createUser() {
		User user = new User(1, "mars", "pvt lte");
		User result = restTemplate.postForObject(CREATE_USER_ENDPOINT_URL, user, User.class);
		System.out.println(result);
	}

}
