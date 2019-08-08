package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.TokenDAO;
import com.ssafy.vo.Token;

@Service
public class TokenServiceImpl implements TokenService{
	
	@Autowired
	TokenDAO dao;

	@Override
	public void insertToken(Token token) {
		dao.insertToken(token);
	}

	@Override
	public List<Token> getTokenList() {
		return dao.getTokenList();
	}

	@Override
	public void deleteToken(String mid) {
		dao.deleteToken(mid);
	}
	

}
