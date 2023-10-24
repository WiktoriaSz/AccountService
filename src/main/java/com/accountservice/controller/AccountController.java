package com.accountservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountservice.dto.UserOutputDto;
import com.accountservice.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/account-service/users")
@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@GetMapping("/{login}")
	public ResponseEntity<UserOutputDto> saveIncomingContainerPlanRequest(@PathVariable String login) {
		return ResponseEntity.ok(accountService.provideUserData(login));
	}
}
