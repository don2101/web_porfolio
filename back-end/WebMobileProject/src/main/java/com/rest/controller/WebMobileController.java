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
	@Autowired(required = true)
	PostService postService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map login(@RequestBody Member member, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		Member m = mService.checkLogin(member.getEmail(), member.getPw());
		if (m != null) {
			map.put("success", "true");
			map.put("mid", m.getMid());
			map.put("grade", m.getGrade());
			logger.info(m.getEmail() + " 로그인");
			return map;
		} else {
			map.put("success", "false");
			logger.info("로그인 실패");
			return map;
		}
	}
//	@RequestMapping(value = "/member", method = RequestMethod.GET, produces = { "application/json;charset=euc-kr" })
//	public List<Member> getMemberList(HttpSession session) {
//		List<Member> memberList = new ArrayList<>();
//		memberList = mService.getMemberList();
//		if (session.getAttribute("sessionId").equals("admin")) {
//			logger.info("멤버리스트 접근");
//		}
//		return memberList;
////		return mService.getMemberList();
//	}
	
	@RequestMapping(value = "/member", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public List<Member> getMemberList() {
		logger.info("멤버리스트 접근");
		return mService.getMemberList();
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.PUT, produces = { "application/json;charset=utf-8" })
	public Map updateMemberList(@RequestBody List<Member> m) {
		HashMap<String, String> map = new HashMap<String, String>();
		int adminCount=0;
		for(Member ma:m) {
			if(ma.getGrade().equals("0")) {
				adminCount++;
			}
		}
		if(adminCount==0) {
			map.put("success", "false");
			map.put("error", "관리자는 무조건 1명 이상이여야 합니다.");
		}else {
			for(int i=0;i<m.size();i++) {
				Member tmp=m.get(i);
//			System.out.println(tmp.getEmail()+" "+tmp.getGrade());
				mService.updateMemberGrade(tmp.getEmail(), tmp.getGrade());
			}
			map.put("success", "true");
		}
		return map;
	}
	
	@RequestMapping(value = "/member/{mid}", method = RequestMethod.GET, produces = {
			"application/json;charset=utf-8" })
	public Map getMemberGrade(@PathVariable("mid") String mid) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("grade", mService.getMemberGrade(mid) );
		return map;
}	
	

//	@RequestMapping(value = "/member/{mid}", method = RequestMethod.GET, produces = {
//			"application/json;charset=utf-8" })
//	public Member getDetailMember(@PathVariable("mid") String mid) {
//		System.out.println(mid);
//		logger.info(mid + " 정보 확인");
//		return mService.getDetailMember(mid);
//	}

	@RequestMapping(value = "/member/{id}", method = RequestMethod.PUT, produces = {
			"application/json;charset=euc-kr" })
	public Map updateMemberInfo(@PathVariable("id") String id, @RequestBody Member member) {
		HashMap<String, String> map = new HashMap<String, String>();
		Member m = mService.getDetailMember(id);
		String beforeUpdatePassword = m.getPw();
		String password = member.getPw();
		System.out.println(id);
		mService.updateMemberInfo(password, id);
		m = mService.getDetailMember(id);
		String afterUpdatePassword = m.getPw();

		if (!beforeUpdatePassword.equals(afterUpdatePassword)) {
			map.put("success", "true");
			logger.info(m.getEmail() + " 업데이트 성공");
		} else {
			map.put("success", "false");
			logger.info("업데이트 실패");
		}
		return map;
	}

	@RequestMapping(value = "/member/{mid}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=utf-8" })
	public Map deleteMemberList(@PathVariable("mid") String mid) {
		HashMap<String, String> map = new HashMap<String, String>();
		mService.deleteMemberList(mid);
		map.put("success", "true");
		return map;

	}

	// Member Create if password is null => Social Member Login
	// password is not null => Local Member Login
	@RequestMapping(value = "/member", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map insertMemberInfo(@RequestBody Member member, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			mService.insertMemberInfo(member);
			session.setAttribute("sessionId", member.getEmail());
		} catch (Exception e) {
			map.put("success", "false");
		}
		logger.info(member.getEmail() + " 회원가입");
		return map;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
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
			System.out.println(portfolio);
			System.out.println(e.getMessage());
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
			Portfolio portfolio = pService.getDetailPortfolio(pfId);
			if (portfolio == null) {
				map.put("success", "true");
			} else {
				map.put("success", "false");
			}
		} catch (Exception e) {
		}
		logger.info("포트폴리오 삭제");
		return map;

	}

	@RequestMapping(value ="/portfolio/{pfId}", method =RequestMethod.PUT , produces = {
			"application/json;charset=euc-kr"})
	public Map updatePortfolioInfo(@PathVariable("pfId") String pfId, @RequestBody Portfolio portfolio){
		HashMap<String, String> map = new HashMap<String, String>(); // 업데이트 성공 여부확인하기위한 map추가 (민재)
		try { // try catch추가 (민재)
			pService.updatePortfolioInfo( pfId, portfolio);
			map.put("success", "true");
		}catch (Exception e) {
			map.put("success", "false");
		}
		
		return map;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//POST CRUD
	
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

}
