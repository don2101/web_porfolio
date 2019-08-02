package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.PostComment;

public interface PostCommentDAO {
	List<PostComment> getPostCommentList(String postId);
	void insert(PostComment pc);
	void deletePostComment(String postcomId);
	void updatePostComment(PostComment pc);
}
