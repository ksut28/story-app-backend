package com.pratilipi.storyapp.models;

import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name = "stories")
public class StoryModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "type")
	private String imageType;

	@Column(name = "imageByte", length = 1000)
	private byte[] imageByte;

	@Column(name = "summary")
	private String summary;

	@Column(name = "content")
	private String content;

	public StoryModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public StoryModel(Long id, String title, String imageType, byte[] imageByte, String summary, String content) {
		super();
		this.id = id;
		this.title = title;
		this.imageType = imageType;
		this.imageByte = imageByte;
		this.summary = summary;
		this.content = content;
	}

	public StoryModel(String title, String imageType, byte[] imageByte, String summary, String content) {
		super();
		this.title = title;
		this.imageType = imageType;
		this.imageByte = imageByte;
		this.summary = summary;
		this.content = content;
	}

	@Override
	public String toString() {
		return "StoryModel [id=" + id + ", title=" + title + ", imageType=" + imageType + ", imageByte="
				+ Arrays.toString(imageByte) + ", summary=" + summary + ", content=" + content + "]";
	}

}