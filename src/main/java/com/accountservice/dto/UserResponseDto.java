package com.accountservice.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {

	private String login;
	private Long id;
	private String avatarUrl;
	private String type;
	private String name;
	private Long publicRepos;
	private Long followers;
	private OffsetDateTime createdAt;

	@JsonCreator
	public UserResponseDto(@JsonProperty("login") String login, @JsonProperty("id") Long id, @JsonProperty("avatar_url") String avatarUrl,
			@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("public_repos") Long publicRepos,
			@JsonProperty("followers") Long followers, @JsonProperty("created_at") OffsetDateTime createdAt) {
		this.login = login;
		this.id = id;
		this.avatarUrl = avatarUrl;
		this.type = type;
		this.name = name;
		this.publicRepos = publicRepos;
		this.followers = followers;
		this.createdAt = createdAt;
	}
}
