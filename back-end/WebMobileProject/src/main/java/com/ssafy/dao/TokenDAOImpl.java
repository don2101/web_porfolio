package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.TokenMapper;
import com.ssafy.vo.Token;

@Repository
public class TokenDAOImpl implements TokenDAO{
	
	@Autowired
	TokenMapper mapper;

	@Override
	public void insertToken(Token token) {
		mapper.insertToken(token);
	}

	@Override
	public List<Token> getTokenList() {
		return mapper.getTokenList();
	}

	@Override
	public void deleteToken(String mid) {
		mapper.deleteToken(mid);
	}
	

}
