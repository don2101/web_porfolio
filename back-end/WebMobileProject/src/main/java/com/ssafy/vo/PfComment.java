package com.ssafy.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class PfComment {
	private	int pfcomId;
	private String content;
	private String date;
	private	int pfId;
	private int mid;
	private String name;
	
}
