package com.pratilipi.storyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratilipi.storyapp.models.StoryCountModel;

public interface StoryTotalCountRepo extends JpaRepository<StoryCountModel, Long> {

}
