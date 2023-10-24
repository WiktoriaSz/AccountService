package com.accountservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.accountservice.model.UserRequest;
import com.accountservice.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class RequestCounterService {

	private final UserRepository userRepository;

	@Transactional
	public void increaseRequestCount(@Valid @NotBlank String login) {
		userRepository.findById(login).ifPresentOrElse(userRequest -> {
			userRequest.setRequestCount(userRequest.getRequestCount() + 1);
			userRepository.save(userRequest);
		}, () -> createNewUser(login));
	}

	private void createNewUser(String login) {
		userRepository.save(new UserRequest(login, 1L));
	}
}
