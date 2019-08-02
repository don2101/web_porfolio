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
	public List<PfComment> getPfCommentList(String pfId) {
		return mapper.selectOne(pfId);
	}

	@Override
	public void insert(PfComment pc) {
		mapper.insert(pc);
	}

	@Override
	public void deletePfComment(String pfcomId) {
		int pfcomIdInt = Integer.parseInt(pfcomId);
		mapper.delete(pfcomIdInt);
	}

	@Override
	public void updatePfComment(PfComment pc) {
		mapper.update(pc);
	}

}
