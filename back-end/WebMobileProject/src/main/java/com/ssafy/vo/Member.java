package com.ssafy.vo;

public class Member {
	private String mid;
	private String email;
	private String pw;
	private String name;
	private String location;
	public Member() {
		// TODO Auto-generated constructor stub
	}	
	
	
	
	public Member(String email, String pw) {
		super();
		this.email = email;
		this.pw = pw;
	}


	public Member(String email, String name, String location) {
		super();
		this.email = email;
		this.name = name;
		this.location = location;
	}

	
	
	

	public Member(String email, String pw, String name, String location) {
		super();
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.location = location;
	}

	public Member(String mid, String email, String pw, String name, String location) {
		super();
		this.mid = mid;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.location = location;
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
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	@Override
	public String toString() {
		return "Member [mid=" + mid + ", email=" + email + ", pw=" + pw + ", name=" + name + "]";
	}
	
	

}
