package com.accountservice.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.accountservice.api.GitHubClient;
import com.accountservice.dto.UserResponseDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class GitHubDataProvider {

	private final GitHubClient gitHubClient;

	public UserResponseDto provideUserAccountData(@Valid @NotBlank String login) {
		UserResponseDto response;
		try {
			response = gitHubClient.retrieveUserData(login);
		} catch (RuntimeException exception) {
			log.error(exception.getMessage());
			throw new IllegalStateException("Error while retrieving user data for user: " + login);
		}

		return response;
	}
}
