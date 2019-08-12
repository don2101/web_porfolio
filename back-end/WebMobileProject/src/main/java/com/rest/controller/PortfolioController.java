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

import com.ssafy.service.PfCommentService;
import com.ssafy.service.PortfolioService;
import com.ssafy.vo.Portfolio;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class PortfolioController {
	Logger logger = LoggerFactory.getLogger("WebMobile.logger");
	
	@Autowired
	PortfolioService pService;
	@Autowired
	PfCommentService pfComService;
	

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
		System.out.println(portfolio);
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
}
