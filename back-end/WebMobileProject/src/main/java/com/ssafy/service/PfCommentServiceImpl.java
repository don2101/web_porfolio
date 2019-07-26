package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.PfCommentDAO;
import com.ssafy.vo.PfComment;
@Service
public class PfCommentServiceImpl implements PfCommentService {
	@Autowired
	PfCommentDAO dao;

	@Override
	public List<PfComment> getPfCommentList() {
		return dao.getPfCommentList();
	}

	@Override
	public void insert(PfComment pc) {
		dao.insert(pc);
	}

	@Override
	public void deletePfComment(String pfcomId) {
		dao.deletePfComment(pfcomId);
	}

	@Override
	public void updatePfComment(PfComment pc) {
		dao.updatePfComment(pc);
	}

}
