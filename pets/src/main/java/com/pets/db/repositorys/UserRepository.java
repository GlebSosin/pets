package com.pets.db.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pets.db.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String email);
	
}
