package com.pratilipi.storyapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratilipi.storyapp.models.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Long> {
	Optional<UserModel> findByUserNameAndPassword(String username, String password);
	Optional<UserModel> findByUserName(String username);
}
