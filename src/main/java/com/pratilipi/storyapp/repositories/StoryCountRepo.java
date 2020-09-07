package com.pratilipi.storyapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratilipi.storyapp.models.StoryLiveCount;

public interface StoryCountRepo extends JpaRepository<StoryLiveCount, Long> {

	Optional<StoryLiveCount> findById(Long id);
}
