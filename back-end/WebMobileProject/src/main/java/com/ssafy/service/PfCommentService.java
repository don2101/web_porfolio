package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.PfComment;

public interface PfCommentService {
	List<PfComment> getPfCommentList();
	void insert(PfComment pc);
}

