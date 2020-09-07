package com.pratilipi.storyapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "story_livecount")
public class StoryLiveCount {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "count")
	private int count;

	public StoryLiveCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoryLiveCount(Long id, int count) {
		super();
		this.id = id;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StoryLiveCount [id=" + id + ", count=" + count + "]";
	}

}