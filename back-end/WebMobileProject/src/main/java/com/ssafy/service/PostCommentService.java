package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.PostComment;

public interface PostCommentService {
	List<PostComment> getPostCommentList(String postId);
	void insert(PostComment pc);
	void deletePostComment(String postcomId);
	void updatePostComment(PostComment pc);
}
