package com.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accountservice.model.UserRequest;

@Repository
public interface UserRepository extends JpaRepository<UserRequest, String> {
}
