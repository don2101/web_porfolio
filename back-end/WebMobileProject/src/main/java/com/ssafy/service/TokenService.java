package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Token;

public interface TokenService {
	public void insertToken(Token token);
	public List<Token> getTokenList();
	public void deleteToken(String mid);
}
