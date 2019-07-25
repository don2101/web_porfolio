package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.PortfolioDAO;
import com.ssafy.vo.Portfolio;

@Service
public class PortfolioServiceImpl implements PortfolioService{
	@Autowired
	PortfolioDAO dao;

	@Transactional
	@Override
	public List<Portfolio> getPortfolioList() {
		return dao.getPortfolioList();
	}

	@Override
	public Portfolio getDetailPortfolio(String id) {
		return dao.getDetailPortfolio(id);
	}

	@Override
	public void insertPortfolioInfo(Portfolio portfolio) {
		dao.insertPortfolioInfo(portfolio);
	}

	@Override
	public void deletePortfolioList(String pfId) {
		dao.deletePortfolioList(pfId);
	}

	@Override
	public void updatePortfolioInfo(String pfId, Portfolio portfolio) {
		dao.updatePortfolioInfo(pfId, portfolio);
	}


}
 