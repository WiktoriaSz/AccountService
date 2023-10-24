package com.accountservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.accountservice.dto.UserResponseDto;
import com.accountservice.dto.UserOutputDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class AccountService {

	private final GitHubDataProvider gitHubDataProvider;
	private final RequestCounterService requestCounterService;

	public UserOutputDto provideUserData(@Valid @NotBlank String login) {
		UserResponseDto userResponseDto = gitHubDataProvider.provideUserAccountData(login);
		UserOutputDto userOutputDto = mapUserInputDataToOutputDto(userResponseDto);
		requestCounterService.increaseRequestCount(login);
		return userOutputDto;
	}

	private UserOutputDto mapUserInputDataToOutputDto(UserResponseDto userResponseDto) {
		return UserOutputDto.builder()
				.id(userResponseDto.getId())
				.login(userResponseDto.getLogin())
				.name(userResponseDto.getName())
				.type(userResponseDto.getType())
				.avatarUrl(userResponseDto.getAvatarUrl())
				.createdAt(userResponseDto.getCreatedAt().toLocalDateTime())
				.calculations(calculate(userResponseDto.getFollowers(), userResponseDto.getPublicRepos()))
				.build();
	}

	private BigDecimal calculate(Long followers, Long publicRepos) {
		return BigDecimal.valueOf((double) 6 / followers * (2 + publicRepos));
	}
}
