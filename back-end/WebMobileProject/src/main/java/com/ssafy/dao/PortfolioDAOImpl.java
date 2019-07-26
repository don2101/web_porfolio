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
	public List<Portfolio> getPortfolioList() {
		return mapper.getPortfolioList();
	}

	@Override
	public Portfolio getDetailPortfolio(String id) {
		return mapper.getDetailPortfolio(id);
	}

	@Override
	public void insertPortfolioInfo(Portfolio portfolio) {
		mapper.insertPortfolioInfo(portfolio);
	}

	@Override
	public void deletePortfolioList(String pfId) {
		mapper.deletePortfolioList(pfId);
	}

	@Override
	public void updatePortfolioInfo(String pfId, Portfolio portfolio) {
		mapper.updatePortfolioInfo(pfId, portfolio);
	}


}
