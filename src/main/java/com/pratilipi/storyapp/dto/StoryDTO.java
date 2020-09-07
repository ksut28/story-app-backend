package com.pratilipi.storyapp.dto;

public class StoryDTO {
	Long storyId;
	Long userId;

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "StoryDTO [storyId=" + storyId + ", userId=" + userId + "]";
	}

}
