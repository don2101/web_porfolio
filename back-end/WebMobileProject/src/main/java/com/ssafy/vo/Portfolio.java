package com.ssafy.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter @ToString
public class Portfolio {
	private String pfId;
	private String title;
	private String content;
	private String count;
	private String date;
	private String img;
	private String mid;
}
