package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Token;

public interface TokenDAO {
	public void insertToken(Token token);
	public List<Token> getTokenList();
	public void deleteToken(String mid);
}
