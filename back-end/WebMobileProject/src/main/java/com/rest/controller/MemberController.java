package com.rest.controller;

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
import com.ssafy.vo.Member;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");

	@Autowired
	MemberService mService;
	
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
}
