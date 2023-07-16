package com.dmart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.model.CurrentUserSession;

public interface UserSessionRepo extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findByUuid(String uuid);
}
