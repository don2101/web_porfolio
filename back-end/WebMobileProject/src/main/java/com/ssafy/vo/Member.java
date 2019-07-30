package com.ssafy.vo;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class Member {
	private String mid;
	private String email;
	private String pw;
	private String name;
	private String grade;
	private int postCount;
	private int pfCount;
	private int postCommentCount;
	private int pfCommentCount;
	
}
