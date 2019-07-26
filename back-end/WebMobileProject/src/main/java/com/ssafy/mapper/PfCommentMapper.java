package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.PfComment;

public interface PfCommentMapper {
	List<PfComment> selectAll();
	void insert(PfComment pc);
}
