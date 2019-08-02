package com.ssafy.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class PostComment {
	private int postcomId;
	private String content;
	private String date;
	private int postId;
	private int mid;
	
}
