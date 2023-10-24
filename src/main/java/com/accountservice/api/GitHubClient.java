package com.accountservice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.accountservice.dto.UserResponseDto;

@FeignClient(name = "${clients.github.name}", url = "${clients.github.url}")
public interface GitHubClient {

	@GetMapping(value = "users/{login}", consumes = MediaType.APPLICATION_JSON_VALUE)
	UserResponseDto retrieveUserData(@PathVariable String login);
}
