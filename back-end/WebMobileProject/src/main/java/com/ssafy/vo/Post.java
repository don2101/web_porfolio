package com.ssafy.vo;

import java.util.ArrayList;

public class Post {
	private String postId;
	private String title;
	private String content;
	private String count;
	private String date;
	private String img;
	private String mid;
	
	public Post() {
		super();
	}

	public Post(String postId, String title, String content, String count, String date, String img, String mid) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.count = count;
		this.date = date;
		this.img = img;
		this.mid = mid;
	}
	
	public Post(String mid, String title, String content) {
		super();
		this.mid = mid;
		this.title = title;
		this.content = content;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	
	
	
	
	
}
