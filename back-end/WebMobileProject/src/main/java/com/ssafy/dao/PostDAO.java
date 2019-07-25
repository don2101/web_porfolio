package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

public interface PostDAO {

	List<Portfolio> getPostList();

	Portfolio getDetailPost(String postId);

	void insertPostInfo(Post post);

	void deletePostList(String postId);

	void updatePostInfo(String postId, Post post);

}
