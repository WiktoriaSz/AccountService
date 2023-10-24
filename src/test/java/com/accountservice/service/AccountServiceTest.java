package com.accountservice.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.accountservice.DataManager;
import com.accountservice.api.GitHubClient;
import com.accountservice.dto.UserOutputDto;
import com.accountservice.dto.UserResponseDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Autowired
	private DataManager dataManager;

	@MockBean
	private GitHubClient gitHubClient;

	@Autowired
	private AccountService service;

	@BeforeEach
	void setUp() {
		dataManager.cleanDatabase();
	}

	@Test
	void provideUserData() {

		// given

		String login = "someLogin";

		UserResponseDto userResponseDto = new UserResponseDto(login, 1L, "url", "type", "name", 3L, 4L, OffsetDateTime.now());
		Mockito.when(gitHubClient.retrieveUserData(login)).thenReturn(userResponseDto);

		// when

		UserOutputDto outputDto = service.provideUserData(login);

		// then

		Assertions.assertNotNull(outputDto);
		assertEquals(1, dataManager.findAllByLogin(login).size());

		assertEquals(userResponseDto.getId(), outputDto.id());
		assertEquals(userResponseDto.getLogin(), outputDto.login());
		assertEquals(userResponseDto.getName(), outputDto.name());
		assertEquals(userResponseDto.getType(), outputDto.type());
		assertEquals(userResponseDto.getAvatarUrl(), outputDto.avatarUrl());
		assertEquals(userResponseDto.getCreatedAt().toLocalDateTime(), outputDto.createdAt());

		BigDecimal calculation = BigDecimal.valueOf((double) 6 / userResponseDto.getFollowers() * (2 + userResponseDto.getPublicRepos()));
		assertEquals(calculation, outputDto.calculations());
	}
}