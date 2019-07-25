package com.ssafy.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Post {
	private String postId;
	private String title;
	private String content;
	private String count;
	private String date;
	private String img;
	private String name;
}
