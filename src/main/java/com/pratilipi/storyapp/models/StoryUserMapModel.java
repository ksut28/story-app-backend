package com.pratilipi.storyapp.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "story_user")
public class StoryUserMapModel {

	@EmbeddedId
	private StoryUserDTO storyUserDTO;

	@Embeddable
	public static class StoryUserDTO implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name = "story_id")
		private Long storyId;

		@Column(name = "user_id")
		private Long userId;

		public StoryUserDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public StoryUserDTO(Long storyId, Long userId) {
			super();
			this.storyId = storyId;
			this.userId = userId;
		}

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
			return "StoryUserDTO [storyId=" + storyId + ", userId=" + userId + "]";
		}

	}

	public StoryUserDTO getStoryUserDTO() {
		return storyUserDTO;
	}

	public void setStoryUserDTO(StoryUserDTO storyUserDTO) {
		this.storyUserDTO = storyUserDTO;
	}

	public StoryUserMapModel(StoryUserDTO storyUserDTO) {
		super();
		this.storyUserDTO = storyUserDTO;
	}

	public StoryUserMapModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StoryUserMapModel [storyUserDTO=" + storyUserDTO + "]";
	}

}
