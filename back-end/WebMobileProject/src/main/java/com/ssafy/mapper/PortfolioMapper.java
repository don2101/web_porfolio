package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.Portfolio;

public interface PortfolioMapper {
	public List<Portfolio> selectAll();
	public Portfolio selectOne(String id);
	public void insert(Portfolio p);
	
}
