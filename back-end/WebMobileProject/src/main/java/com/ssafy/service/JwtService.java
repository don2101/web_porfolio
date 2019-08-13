package com.ssafy.service;

import com.ssafy.vo.Member;

public interface JwtService {
	public String makeJwt(Member m);
	public boolean checkJwt(String jwt);
	public Member readJwt(String jwt);
	public String getId(String jwt);
}