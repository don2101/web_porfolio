package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.PostCommentDAO;
import com.ssafy.vo.PostComment;

@Service
public class PostCommentServiceImpl implements PostCommentService {
	@Autowired
	PostCommentDAO dao;
	
	@Override
	public List<PostComment> getPostCommentList(String postId) {
		return dao.getPostCommentList(postId);
	}

	@Override
	public void insert(PostComment pc) {
		dao.insert(pc);
	}

	@Override
	public void deletePostComment(String postcomId) {
		dao.deletePostComment(postcomId);
	}

	@Override
	public void updatePostComment(PostComment pc) {
		dao.updatePostComment(pc);
	}

}
