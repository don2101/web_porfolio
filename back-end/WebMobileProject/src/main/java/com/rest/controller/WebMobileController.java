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

import com.ssafy.mapper.MemberMapper;
import com.ssafy.service.MemberService;
import com.ssafy.service.PortfolioService;
import com.ssafy.vo.Member;
import com.ssafy.vo.Portfolio;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class WebMobileController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");

	@Autowired
	MemberService mService;	
	@Autowired
	PortfolioService pService;
	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public List<Member> userList(){
////		logger.info("筌롫뗄�뵥占쎈읂占쎌뵠筌욑옙 占쎌젔占쎈꺗");
//		return ms.selectAll();
//	}

	// 濡쒓렇�씤 ID, Password �솗�씤
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map login(@RequestBody Member member, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		Member m = mService.checkLogin(member.getEmail(), member.getPw());
		if (m != null) {
			map.put("success", "true");
			map.put("mid", m.getMid());
			map.put("loc", m.getLocation());
			logger.info(m.getEmail()+ " 로그인");
			return map;
		} else { // 蹂댁셿 �긽 �븘�씠�뵒, �뙣�뒪�썙�뱶 �븯�굹�씪�룄 ��由� �떆 false 諛섑솚
			map.put("success", "false");
			logger.info("로그인 실패");
			return map;
		}
	}
	
	//愿�由ъ옄 �엯�옣�뿉�꽌 �궗�슜�옄 由ъ뒪�듃�뱾 �솗�씤
	
	  @RequestMapping(value = "/member", method= RequestMethod.GET, produces= {"application/json;charset=euc-kr"})
	  public List<Member> getMemberList(HttpSession session){
		  List<Member> memberList = new ArrayList<>();
		  if(session.getAttribute("sessionId").equals("admin")) {
			  memberList =mService.getMemberList();
			  logger.info("멤버리스트 접근");
		  }
		  return memberList;
	  }
	
	 //愿�由ъ옄 �엯�옣�뿉�꽌 �궗�슜�옄 �뵒�뀒�씪 �젙蹂� �솗�씤
	  @RequestMapping(value = "/member/{id}", method= RequestMethod.GET, produces= {"application/json;charset=euc-kr"})
	  public Member getMemberList(@PathVariable("id") String email ,HttpSession session ){
		  System.out.println(email);
		  Member member = mService.getDetailMember(email);
		  logger.info(email + " 정보 확인");
		  return member;
	  }
	  //�쉶�썝 鍮꾨�踰덊샇 �닔�젙
	  @RequestMapping(value = "/member/{id}", method= RequestMethod.PUT, produces= {"application/json;charset=euc-kr"})
	  public Map updateMemberInfo(@PathVariable("id") String id ,HttpSession session, @RequestBody Member member){
		  HashMap<String, String> map = new HashMap<String, String>();
		  Member m = mService.getDetailMember(id);
		  String beforeUpdatePassword = m.getPw();
		  String password = member.getPw();
		  System.out.println(id);
		  mService.updateMemberInfo(password , id);
		  m = mService.getDetailMember(id);
		  String afterUpdatePassword = m.getPw();
		 
		  if(!beforeUpdatePassword.equals(afterUpdatePassword)) {
			  map.put("success", "true");
			  logger.info(m.getEmail()+" 업데이트 성공");
		  }else {
			  map.put("success", "false");
			  logger.info("업데이트 실패");
		  }
		  return map;
	  }
	  
	  @RequestMapping(value = "/member/{id}", method= RequestMethod.DELETE, produces= {"application/json;charset=euc-kr"})
	  public Map DeleteMemberList(@PathVariable("id") String email){
		  HashMap<String, String> map = new HashMap<String, String>();
		  mService.deleteMemberList(email);
		  Member member = mService.getDetailMember(email);
		  if(member == null) {
			  map.put("success", "true");
			  logger.info(member.getEmail()+" 회원삭제");
		  }else {
			  map.put("success", "false");
			  logger.info("삭제 실패");
		  }
		  
		  return map;
		  
	  }
	 
	// �쉶�썝媛��엯 湲곕뒫 諛� �쇅遺�濡쒓렇�씤 �떆 �뜲�씠�꽣踰좎씠�뒪�뿉 �젙蹂� ���옣
	@RequestMapping(value = "/member", method = RequestMethod.POST, produces = {"application/json;charset=euc-kr" })
	public Map insertMemberInfo(@RequestBody Member member, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			if (member.getLocation().equals("2")) { // 외부 로그인
				mService.insertMemberInfoSns(member.getEmail(), member.getName(), member.getLocation());
				map.put("success", "true");

			} else if (member.getLocation().equals("3")) {
				mService.insertMemberInfoSns(member.getEmail(), member.getName(), member.getLocation());
				map.put("success", "true");

			} else if (member.getLocation().equals("1")) { // 회원가입
				mService.insertMemberInfo(member);
				session.setAttribute("sessionId", member.getEmail());
				map.put("success", "true");
			}
			
			
		} catch (Exception e) {
			map.put("success", "false");
		}
		logger.info(member.getEmail()+" 회원가입");
		return map;
	}
	
	
	@RequestMapping(value="/portfolio", method = RequestMethod.POST, produces= {"application/json;charset=euc-kr"})
	public Map insertPortfolio(@RequestBody Portfolio portfolio) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			pService.insert(portfolio);
			logger.info("포트폴리오 저장");
			map.put("success", "true");
		}catch(Exception e){
			logger.info("포트폴리오 저장 실패");
			map.put("success", "fail");
		}
		return map;
	}
	
	@RequestMapping(value="/portfolio", method = RequestMethod.GET)
	public List<Portfolio> getPortfolioList(){
		List<Portfolio> list=pService.selectAll();
		logger.info("포트폴리오 리스트 불러옴");
		return pService.selectAll();
	}
	@RequestMapping(value="/portfolio/{pfId}", method = RequestMethod.GET, produces= {"application/json;"})
	public Portfolio getPortfolio(@PathVariable("pfId") String id) {
		return pService.selectOne(id);
	}
	

}
