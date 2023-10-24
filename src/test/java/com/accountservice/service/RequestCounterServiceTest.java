package com.accountservice.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accountservice.DataManager;
import com.accountservice.model.UserRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RequestCounterServiceTest {

	@Autowired
	private DataManager dataManager;

	@Autowired
	private RequestCounterService service;

	@BeforeEach
	void setUp() {
		dataManager.cleanDatabase();
	}

	@Test
	void increaseRequestCount_UserRequestDoesNotExists() {

		// given

		String login = "someLogin";
		Assertions.assertTrue(dataManager.findAllByLogin(login).isEmpty());

		// when

		service.increaseRequestCount(login);

		// then

		List<UserRequest> userRequests = dataManager.findAllByLogin(login);
		assertEquals(1, userRequests.size());

		UserRequest UserRequest = userRequests.get(0);
		assertEquals(login, UserRequest.getLogin());
		assertEquals(1, UserRequest.getRequestCount());
	}

	@Test
	void increaseRequestCount_UserRequestExists() {

		// given

		String login = "someLogin";
		UserRequest UserRequest = dataManager.userRequest(login, 2L);

		service.increaseRequestCount(login);

		// then

		List<UserRequest> UserRequests = dataManager.findAllByLogin(login);
		assertEquals(1, UserRequests.size());

		UserRequest = UserRequests.get(0);
		assertEquals(login, UserRequest.getLogin());
		assertEquals(3, UserRequest.getRequestCount());
	}

}