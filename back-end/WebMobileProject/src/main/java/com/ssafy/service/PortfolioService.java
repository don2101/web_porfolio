package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Portfolio;

public interface PortfolioService {
	public List<Portfolio> getPortfolioList();
	public Portfolio getDetailPortfolio(String pfId);
	public void insertPortfolioInfo(Portfolio portfolio);
	public void deletePortfolioList(String pfId);
	public void updatePortfolioInfo(String pfId, Portfolio portfolio);
}
