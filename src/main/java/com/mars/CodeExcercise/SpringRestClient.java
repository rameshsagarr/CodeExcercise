package com.mars.CodeExcercise;

import org.springframework.web.client.RestTemplate;

import com.mars.CodeExcercise.model.User;

public class SpringRestClient {
	
	private static final String GET_USERS_ENDPOINT_URL = "http://localhost:9090/api/v1/users";
	private static final String GET_USERCOUNT_ENDPOINT_URL = "http://localhost:8080/api/v1/userscount";
	private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:9090/api/v1/users";
	private static final String UPDATE_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/user/{id}";
	private static final String DELETE_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/user/{id}";
	private static RestTemplate restTemplate = new RestTemplate();
	public static void main(String[] args) {
		SpringRestClient restclient=new SpringRestClient();
		restclient.createUser();
		/*restclient.userCount();
		restclient.updateUser();
		restclient.deleteUSer();*/
		
	}
	private void createUser() {
		// TODO Auto-generated method stub
		
		User user=new User(1,"mars","pvt lte");
		RestTemplate restTemplate = new RestTemplate();
		User result=restTemplate.postForObject(CREATE_USER_ENDPOINT_URL, user,User.class);
		System.out.println(result);
		
		
	}

}
