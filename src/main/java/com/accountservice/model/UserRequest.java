package com.accountservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequest {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "LOGIN")
	public String login;

	@Column(name = "REQUEST_COUNT")
	private Long requestCount;
}
