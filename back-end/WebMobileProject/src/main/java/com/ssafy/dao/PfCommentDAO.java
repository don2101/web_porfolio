package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.PfComment;

public interface PfCommentDAO {
	List<PfComment> getPfCommentList();
	void insert(PfComment pc);
}
