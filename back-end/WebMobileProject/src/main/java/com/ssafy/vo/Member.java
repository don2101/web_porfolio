package com.ssafy.vo;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Member {
	private String mid;
	private String email;
	private String pw;
	private String name;
	private String grade;
}
