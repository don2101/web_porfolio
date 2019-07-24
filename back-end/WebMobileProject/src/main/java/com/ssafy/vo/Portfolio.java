package com.ssafy.vo;

import java.util.ArrayList;

/**
 * @author multicampus
 *
 */
public class Portfolio {
	private String pfId;
	private String title;
	private String content;
	private String count;
	private String date;
	private String img;
	private String name;
	
	public Portfolio() {}

	public Portfolio(String pfId, String title, String content, String count, String date, String img, String name) {
		this.pfId = pfId;
		this.title = title;
		this.content = content;
		this.count = count;
		this.date = date;
		this.img = img;
		this.name = name;
	}

	public String getPfId() {
		return pfId;
	}

	public void setPfId(String pfId) {
		this.pfId = pfId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Portfolio [pfId=" + pfId + ", title=" + title + ", content=" + content + ", count=" + count + ", date="
				+ date + ", img=" + img + ", name=" + name + "]";
	}

	
	
}
