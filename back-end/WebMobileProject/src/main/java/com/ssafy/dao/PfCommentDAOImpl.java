package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.mapper.PfCommentMapper;
import com.ssafy.vo.PfComment;

@Repository
public class PfCommentDAOImpl implements PfCommentDAO{
	@Autowired
	PfCommentMapper mapper;

	@Override
	public List<PfComment> getPfCommentList() {
		return mapper.selectAll();
	}

	@Override
	public void insert(PfComment pc) {
		mapper.insert(pc);
	}

}
