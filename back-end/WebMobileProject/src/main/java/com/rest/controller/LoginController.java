package com.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.service.JwtService;
import com.ssafy.service.MemberService;
import com.ssafy.service.TokenService;
import com.ssafy.vo.Member;
import com.ssafy.vo.Token;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class LoginController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");

	@Autowired
	MemberService mService;
	@Autowired
	JwtService jwtService;
	@Autowired
	TokenService tokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map login(@RequestBody Member member, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		Member m = mService.checkLogin(member.getEmail(), member.getPw());
		if (m != null) {
			map.put("success", "true");
			map.put("mid", m.getMid());
			map.put("grade", m.getGrade());
//			tokenService.insertToken(token);
			// jwt 생성 및 Map에 담기
			String jwt = jwtService.makeJwt(m);
			map.put("jwt", jwt);
			logger.info("jwt 생성: " + jwt);
			logger.info(m.getEmail() + " 로그인");
			return map;
		} else {
			map.put("success", "false");
			logger.info("로그인 실패");
			return map;
		}
	}

	@RequestMapping(value = "/token", method = RequestMethod.POST, produces = { "application/json;charset=euc-kr" })
	public Map insertToken(@RequestBody Token token, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		tokenService.deleteToken(token.getMid());
		tokenService.insertToken(token);
		map.put("success", "true");
		return map;
	}

	@RequestMapping(value = "/token", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
	public String[] getTokenList() {
		logger.info("토큰 접근");
		List<Token> list = tokenService.getTokenList();
		String[] result = new String[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i).getValue();
		}
		return result;
	}

	@RequestMapping(value = "/token/{mid}", method = RequestMethod.DELETE, produces = {
			"application/json;charset=utf-8" })
	public Map deleteToken(@PathVariable("mid") String mid) {
		HashMap<String, String> map = new HashMap<String, String>();
		tokenService.deleteToken(mid);
		map.put("success", "true");
		return map;
	}
	
	@GetMapping("/jwt/auth")
	public Map checkAuth(HttpServletRequest res) {
		HashMap<String, String> map = new HashMap<String, String>();
		String jwt = res.getParameter("jwt");
		// 유효화 검사.. 유효하면 갱신, 그렇지 않으면 폐기
		// 유효화 검사에 accesstoken과 refreshToken 2개를 발
		if (jwtService.checkJwt(jwt)) {
			logger.info("jwt 유효");
			map.put("success", "true");
			Member m = jwtService.readJwt(jwt);
			map.put("mid", m.getMid());
			map.put("name", m.getName());
			return map;
		} else {
			logger.info("jwt 유효하지 않음");
			logger.info(jwt);
			map.put("success", "invalidToken");
			return map;
		}
	}
}
