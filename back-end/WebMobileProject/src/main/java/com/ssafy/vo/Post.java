package com.ssafy.vo;

import java.util.ArrayList;

public class Post {
	private String postId;
	private String title;
	private String content;
	private String count;
	private String date;
	private ArrayList<String> postImg;
	
	public Post() {
		super();
	}

	public Post(String postId, String title, String content, String count, String date, ArrayList<String> postImg) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.count = count;
		this.date = date;
		this.postImg = postImg;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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
	
	
	
	
	
	
}
