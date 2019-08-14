package com.ssafy.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssafy.vo.Member;

import io.jsonwebtoken.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtServiceImpl implements JwtService {

	private String secretKey = "ThisisJinhoSecretKeyWelcomeMyFirstJwt";

    private Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Override
    public String makeJwt(Member m) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map= new HashMap<String, Object>();

        map.put("mid", m.getMid());
        map.put("email", m.getEmail());
        map.put("name", m.getName());
        map.put("grade", m.getGrade());
        map.put("postCount", m.getPostCount());
        map.put("pfCount", m.getPfCount());
        map.put("postCommentCount", m.getPostCommentCount());
        map.put("pfCommentCount", m.getPfCommentCount());
        logger.info("jwt Token 생성: " + m.getName() + " " + m.getEmail());

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    @Override
    public boolean checkJwt(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰	
            logger.info("expireTime :" + claims.getExpiration());
            return true;
        } catch (ExpiredJwtException exception) {
            logger.info("토큰 만료");
            return false;
        } catch (JwtException exception) {
            logger.info("토큰 변조");
            return false;
        }
    }

	@Override
	public Member readJwt(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰	
		Member m = new Member();
        m.setMid((String) claims.get("mid"));
        m.setName((String) claims.get("name"));
        logger.info("mid :" + m.getMid());
        logger.info("name :" + m.getName());
		return m;
	}

	@Override
	public String getId(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(jwt).getBody(); // 정상 수행된다면 해당 토큰은 정상토큰	
		return (String) claims.get("mid");
	}

}

