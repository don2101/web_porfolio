package com.ssafy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.vo.Portfolio;

public interface PortfolioMapper {
	public List<Portfolio> getPortfolioList();
	public Portfolio getDetailPortfolio(String id);
	public void insertPortfolioInfo(Portfolio portfolio);
	public void deletePortfolioList(String pfId);
	public void updatePortfolioInfo(@Param("pfId") String pfId, @Param("portfolio") Portfolio portfolio);
	
}
