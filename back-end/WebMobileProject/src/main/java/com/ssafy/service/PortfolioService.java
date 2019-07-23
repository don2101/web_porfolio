package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Portfolio;

public interface PortfolioService {
	public List<Portfolio> selectAll();
	public Portfolio selectOne(String id);
	public void insert(Portfolio p);
}
