package com.ssafy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.mapper.PostMapper;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

public class PostDAOImpl implements PostDAO{
	
	@Autowired
	PostMapper mapper;
	
	@Override
	public List<Portfolio> getPostList() {
		return mapper.getPostList();
	}

	@Override
	public Portfolio getDetailPost(String postId) {
		return mapper.getDetailPost(postId);
	}

	@Override
	public void insertPostInfo(Post post) {
		mapper.insertPostInfo(post);
	}

	@Override
	public void deletePostList(String postId) {
		mapper.deletePostList(postId);
	}

	@Override
	public void updatePostInfo(String postId, Post post) {
		mapper.updatePostInfo(postId, post);
	}

}
