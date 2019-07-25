package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.dao.PostDAO;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostDAO dao;
	
	@Override
	public List<Post> getPostList() {
		return dao.getPostList();
	}

	@Override
	public Portfolio getDetailPost(String postId) {
		return dao.getDetailPost(postId);
	}

	@Override
	public void insertPostInfo(Post post) {
		dao.insertPostInfo(post);
	}

	@Override
	public void deletePostList(String postId) {
		dao.deletePostList(postId);
	}

	@Override
	public void updatePostInfo(String postId, Post post) {
		dao.updatePostInfo(postId, post);
	}

}
