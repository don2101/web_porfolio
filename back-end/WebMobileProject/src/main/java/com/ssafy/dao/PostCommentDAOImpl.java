package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssafy.mapper.PostCommentMapper;
import com.ssafy.vo.PostComment;

@Repository
public class PostCommentDAOImpl implements PostCommentDAO {
	@Autowired
	PostCommentMapper mapper;
	
	@Override
	public List<PostComment> getPostCommentList(String postId) {
		return mapper.selectOne(postId);
	}

	@Override
	public void insert(PostComment pc) {
		mapper.insert(pc);
	}

	@Override
	public void deletePostComment(String postcomId) {
		int postcomIdInt = Integer.parseInt(postcomId);
		mapper.delete(postcomIdInt);
	}

	@Override
	public void updatePostComment(PostComment pc) {
		mapper.update(pc);
	}

}
