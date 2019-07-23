package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.PortfolioMapper;
import com.ssafy.vo.Portfolio;

@Repository
public class PortfolioDAOImpl implements PortfolioDAO {
	@Autowired
	PortfolioMapper mapper;
	
	@Override
	public List<Portfolio> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public Portfolio selectOne(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public void insert(Portfolio p) {
		mapper.insert(p);
	}

}
