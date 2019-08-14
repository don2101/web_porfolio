package com.ssafy.mapper;

import java.util.List;

import com.ssafy.vo.PostComment;

public interface PostCommentMapper {
	List<PostComment> selectOne(String postId);
	void insert(PostComment pc);
	void delete(int postcomId);
	void update(PostComment pc);
}
