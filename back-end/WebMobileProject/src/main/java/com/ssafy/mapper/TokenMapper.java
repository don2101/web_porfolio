package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.Token;

public interface TokenMapper {	
	public void insertToken(Token token);
	public List<Token> getTokenList();
	public void deleteToken(String mid);
}
