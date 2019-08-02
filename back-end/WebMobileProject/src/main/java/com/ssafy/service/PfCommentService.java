package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.PfComment;

public interface PfCommentService {
	List<PfComment> getPfCommentList(String pfId);
	void insert(PfComment pc);
	void deletePfComment(String pfcomId);
	void updatePfComment(PfComment pc);
}

