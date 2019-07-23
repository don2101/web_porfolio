package com.ssafy.vo;

import java.util.ArrayList;

public class Post {
	private String id;
	private String title;
	private String content;
	private String count;
	private String date;
	private ArrayList<String> postImg;
	
	
	
	public Post() {}

	public Post(String id, String title, String content, String count, String date, ArrayList<String> postImg) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.count = count;
		this.date = date;
		this.postImg = postImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<String> getPostImg() {
		return postImg;
	}

	public void setPostImg(ArrayList<String> postImg) {
		this.postImg = postImg;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", count=" + count + ", date=" + date
				+ ", postImg=" + postImg + "]";
	}
	
	
	
}
