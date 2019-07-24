package com.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.MemberService;
import com.ssafy.service.PortfolioService;
import com.ssafy.service.PostService;
import com.ssafy.vo.Member;
import com.ssafy.vo.Portfolio;
import com.ssafy.vo.Post;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class WebMobileController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");

	@Autowired
	MemberService mService;
	@Autowired
	PortfolioService pService;
	@Autowired
	PostService postService;

//	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
//	public Map login(@RequestBody Member member, HttpSession session) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		Member m = mService.checkLogin(member.getEmail(), member.getPw());
//		if (m != null) {
//			map.put("success", "true");
//			map.put("mid", m.getMid());
//			map.put("loc", m.getLocation());
//			logger.info(m.getEmail() + " 로그인");
//			return map;
//		} else {
//			map.put("success", "false");
//			logger.info("로그인 실패");
//			return map;
//		}
//	}
//
//	@RequestMapping(value = "/member", method = RequestMethod.GET, produces = { "application/json;charset=euc-kr" })
//	public List<Member> getMemberList(HttpSession session) {
//		List<Member> memberList = new ArrayList<>();
//		if (session.getAttribute("sessionId").equals("admin")) {
//			memberList = mService.getMemberList();
//			logger.info("멤버리스트 접근");
//		}
//		return memberList;
//	}
//
//	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = {
//			"application/json;charset=euc-kr" })
//	public Member getMemberList(@PathVariable("id") String email, HttpSession session) {
//		System.out.println(email);
//		Member member = mService.getDetailMember(email);
//		logger.info(email + " 정보 확인");
//		return member;
//	}
//
//	@RequestMapping(value = "/member/{id}", method = RequestMethod.PUT, produces = {
//			"application/json;charset=euc-kr" })
//	public Map updateMemberInfo(@PathVariable("id") String id, HttpSession session, @RequestBody Member member) {
//	
//		HashMap<String, String> map = new HashMap<String, String>();
//		Member m = mService.getDetailMember(id);
//		String beforeUpdatePassword = m.getPw();
//		String password = member.getPw();
//		System.out.println(id);
//		mService.updateMemberInfo(password, id);
//		m = mService.getDetailMember(id);
//		String afterUpdatePassword = m.getPw();
//
//		if (!beforeUpdatePassword.equals(afterUpdatePassword)) {
//			map.put("success", "true");
//			logger.info(m.getEmail() + " 업데이트 성공");
//		} else {
//			map.put("success", "false");
//			logger.info("업데이트 실패");
//		}
//		return map;
//	}
//
//	@RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE, produces = {
//			"application/json;charset=euc-kr" })
//	public Map deleteMemberList(@PathVariable("id") String email) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		mService.deleteMemberList(email);
//		Member member = mService.getDetailMember(email);
//		if (member == null) {
//			map.put("success", "true");
//			logger.info(member.getEmail() + " 회원삭제");
//		} else {
//			map.put("success", "false");
//			logger.info("삭제 실패");
//		}
//
//		return map;
//
//	}
//
//	@RequestMapping(value = "/member", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
//	public Map insertMemberInfo(@RequestBody Member member, HttpSession session) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		try {
//			if (member.getLocation().equals("2")) { // 외부 로그인
//				mService.insertMemberInfoSns(member.getEmail(), member.getName(), member.getLocation());
//				map.put("success", "true");
//
//			} else if (member.getLocation().equals("3")) {
//				mService.insertMemberInfoSns(member.getEmail(), member.getName(), member.getLocation());
//				map.put("success", "true");
//
//			} else if (member.getLocation().equals("1")) { // 회원가입
//				mService.insertMemberInfo(member);
//				session.setAttribute("sessionId", member.getEmail());
//				map.put("success", "true");
//			}
//
//		} catch (Exception e) {
//			map.put("success", "false");
//		}
//		logger.info(member.getEmail() + " 회원가입");
//		return map;
//	}

	// Portfolio CRUD

	@RequestMapping(value = "/portfolio", method = RequestMethod.GET)
	public List<Portfolio> getPortfolioList() {
		List<Portfolio> list = pService.getPortfolioList();
		logger.info("포트폴리오 리스트 불러옴");
		return pService.getPortfolioList();
	}

	@RequestMapping(value = "/portfolio/{pfId}", method = RequestMethod.GET)
	public Portfolio getDetailPortfolioList(@PathVariable String pfId) {
		Portfolio portfolio = pService.getDetailPortfolio(pfId);
		return portfolio;
	}

	@RequestMapping(value = "/portfolio", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map insertPortfolioInfo(@RequestBody Portfolio portfolio) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			pService.insertPortfolioInfo(portfolio);
			logger.info("포트폴리오 저장");
			map.put("success", "true");
		} catch (Exception e) {
			logger.info("포트폴리오 저장 실패");
			map.put("success", "fail");
		}
		return map;
	}

	@RequestMapping(value = "/portfolio/{pfId}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=euc-kr" })
	public Map deletePortfolioList(@PathVariable("pfId") String pfId) {
		HashMap<String, String> map = new HashMap<String, String>();
		pService.deletePortfolioList(pfId);
		try {
			pService.getDetailPortfolio(pfId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}


		return map;

	}
	
	@RequestMapping(value ="/portfolio/{pfId}", method =RequestMethod.PUT , produces = {
			"application/json;charset=euc-kr"})
	public Map updatePortfolioInfo(@PathVariable String pfId, @RequestBody Portfolio portfolio){
		System.out.println(portfolio.getContent());
		System.out.println(portfolio.getTitle());
		System.out.println(pfId);
		pService.updatePortfolioInfo( pfId, portfolio);
		
		return null;
	}
	
	
	//POST CRUD
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public List<Portfolio> getPostList() {
		List<Portfolio> list = postService.getPostList();
		logger.info("포트폴리오 리스트 불러옴");
		return pService.getPortfolioList();
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public Portfolio getDetailPostList(@PathVariable String postId) {
		Portfolio portfolio = postService.getDetailPost(postId);
		return portfolio;
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

		return map;

	}
	
	@RequestMapping(value ="/post/{postId}", method =RequestMethod.PUT , produces = {
			"application/json;charset=euc-kr"})
	public Map updatePostInfo(@PathVariable String postId, @RequestBody Post post){
		System.out.println(postId);
		postService.updatePostInfo( postId, post);
		
		return null;
	}

}
