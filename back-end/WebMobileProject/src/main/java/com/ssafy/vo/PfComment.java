package com.ssafy.vo;

import java.sql.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PfComment {
	int pfcomId;
	String content;
	String date;
	int pfId;
	int mid;
	
	public PfComment(int pfcomId, String content, String date, int pfId, int mid) {
		super();
		this.pfcomId = pfcomId;
		this.content = content;
		this.date = date;
		this.pfId = pfId;
		this.mid = mid;
	}
	
	public int getPfcomId() {
		return pfcomId;
	}
	public void setPfcomId(int pfcomId) {
		this.pfcomId = pfcomId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPfId() {
		return pfId;
	}
	public void setPfId(int pfId) {
		this.pfId = pfId;
	}
	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("pfComment [id=").append(pfcomId).append(", content=").append(content).append(", date=").append(date)
				.append(", pfId=").append(pfId).append(", mid=").append(mid).append("]");
		return builder.toString();
	}
	
}
