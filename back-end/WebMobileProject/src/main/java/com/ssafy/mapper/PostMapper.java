package com.ssafy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

public interface PostMapper {

	List<Post> getPostList();

	Portfolio getDetailPost(String postId);

	void insertPostInfo(Post post);

	void deletePostList(String postId);

	void updatePostInfo(@Param("postId") String postId,@Param("post") Post post);

}
