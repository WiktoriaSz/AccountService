package com.accountservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record UserOutputDto(Long id, String login, String name, String type, String avatarUrl, LocalDateTime createdAt, BigDecimal calculations) {
}
