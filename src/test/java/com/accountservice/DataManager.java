package com.accountservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accountservice.model.UserRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Transactional
@Service
public class DataManager {

	@PersistenceContext
	private EntityManager entityManager;

	public void cleanDatabase() {
		entityManager.createQuery("DELETE FROM " + UserRequest.class.getName()).executeUpdate();
	}

	public List<UserRequest> findAllByLogin(String login) {
		return entityManager.createQuery("FROM " + UserRequest.class.getName() + " u WHERE u.login =:login", UserRequest.class)
				.setParameter("login", login).getResultList();
	}

	public UserRequest userRequest(String login, Long requestCount) {
		UserRequest user = new UserRequest(login, requestCount);
		entityManager.persist(user);
		entityManager.flush();
		return user;
	}
}
