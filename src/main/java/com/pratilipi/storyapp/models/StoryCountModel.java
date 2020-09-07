package com.pratilipi.storyapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "story_total_count")
public class StoryCountModel {

	@Id
	@Column
	private Long id;

	@Column
	private Long count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public StoryCountModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoryCountModel(Long id, Long count) {
		super();
		this.id = id;
		this.count = count;
	}

	@Override
	public String toString() {
		return "StoryCountModel [id=" + id + ", count=" + count + "]";
	}

}
