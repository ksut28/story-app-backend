package com.pratilipi.storyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratilipi.storyapp.models.StoryModel;

public interface StoryRepo extends JpaRepository<StoryModel, Long> {

}
