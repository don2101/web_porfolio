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
	public List<Portfolio> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Portfolio selectOne(String id) {
		return dao.selectOne(id);
	}

	@Override
	public void insert(Portfolio p) {
		dao.insert(p);
	}
}
