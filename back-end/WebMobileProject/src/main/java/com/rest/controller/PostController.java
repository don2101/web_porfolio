package com.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.PostCommentService;
import com.ssafy.service.PostService;
import com.ssafy.vo.Post;
import com.ssafy.vo.PostComment;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class PostController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");

	@Autowired(required = true)
	PostService postService;
	
	@Autowired
	PostCommentService postComService;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public List<Post> getPostList() {
		List<Post> list = postService.getPostList();
		logger.info("포트폴리오 리스트 불러옴");
		return list;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public Post getDetailPostList(@PathVariable String postId) {
		Post post = postService.getDetailPost(postId);
		return post;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map insertPostInfo(@RequestBody Post post) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			postService.insertPostInfo(post);
			logger.info("포트폴리오 저장");
			map.put("success", "true");
		} catch (Exception e) {
			logger.info("포트폴리오 저장 실패");
			map.put("success", "fail");
		}
		return map;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=euc-kr" })
	public Map deletePostList(@PathVariable("postId") String postId) {
		HashMap<String, String> map = new HashMap<String, String>();
		postService.deletePostList(postId);
		try {
			Post post = postService.getDetailPost(postId);
			if (post == null) {
				map.put("success", "true");
			} else {
				map.put("success", "false");
			}
		} catch (Exception e) {
		}

		return map;

	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.PUT, produces = {
			"application/json;charset=euc-kr" })
	public Map updatePostInfo(@PathVariable String postId, @RequestBody Post post) {
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			postService.updatePostInfo(postId, post);	
			map.put("success", "true");
		} catch (Exception e) {
			map.put("success", "false");
		}

		return map;
	}
	
	// Post Comment
	
	@RequestMapping(value = "/post/comments/{postId}", method = RequestMethod.GET)
	public List<PostComment> getPostCommentList(@PathVariable("postId") String postId) {
		logger.info("포스트 댓글 불러옴");
		return postComService.getPostCommentList(postId);
	}

	@RequestMapping(value = "/post/comments", method = RequestMethod.POST)
	public Map insertPostComment(@RequestBody PostComment postComment) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			postComService.insert(postComment);
			logger.info("포스트 댓글 저장");
			map.put("success", "true");
		} catch (Exception e) {
			logger.info("포스트 댓글 저장 실패");
			map.put("success", "fail");
		}
		return map;
	}

	@RequestMapping(value = "/post/comments/{postcomId}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=euc-kr" })
	public Map deletePostComment(@PathVariable("postcomId") String postcomId, RequestBody res) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {

			postComService.deletePostComment(postcomId);
			map.put("success", "true");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("success", "false");
		}
		return map;

	}

	@RequestMapping(value = "/post/comments/", method = RequestMethod.PUT, produces = {
			"application/json;charset=euc-kr" })
	public Map updatePostComment(@RequestBody PostComment postComment) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			postComService.updatePostComment(postComment);
			map.put("success", "true");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("success", "false");
		}
		return map;
	}
}
