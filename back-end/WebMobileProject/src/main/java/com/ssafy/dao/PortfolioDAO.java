package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Portfolio;

public interface PortfolioDAO {
	public List<Portfolio> getPortfolioList();
	public Portfolio getDetailPortfolio(String id);
	public void insertPortfolioInfo(Portfolio portfolio);
	public void deletePortfolioList(String pfId);
	public void updatePortfolioInfo(String pfId, Portfolio portfolio);
}
