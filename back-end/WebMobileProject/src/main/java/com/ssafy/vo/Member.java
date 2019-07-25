package com.ssafy.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Member {
	private String mid;
	private String email;
	private String pw;
	private String name;
	private String grade;
}
