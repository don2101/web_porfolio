package com.ssafy.vo;

public class Member {
	private String mid;
	private String email;
	private String pw;
	private String name;
	private String grade;
	
	
	
	public Member() {
		super();
	}



	public Member(String mid, String email, String pw, String name, String grade) {
		super();
		this.mid = mid;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.grade = grade;
	}



	public String getMid() {
		return mid;
	}



	public void setMid(String mid) {
		this.mid = mid;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPw() {
		return pw;
	}



	public void setPw(String pw) {
		this.pw = pw;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	

}
