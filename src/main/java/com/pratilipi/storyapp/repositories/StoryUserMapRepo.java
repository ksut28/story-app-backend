package com.pratilipi.storyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratilipi.storyapp.models.StoryUserMapModel;
import com.pratilipi.storyapp.models.StoryUserMapModel.StoryUserDTO;

public interface StoryUserMapRepo extends JpaRepository<StoryUserMapModel, StoryUserDTO> {

}
