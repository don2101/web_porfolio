package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Portfolio;

public interface PortfolioDAO {
	public List<Portfolio> selectAll();
	public Portfolio selectOne(String id);
	public void insert(Portfolio p);
}
