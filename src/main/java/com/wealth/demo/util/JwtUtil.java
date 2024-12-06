package com.wealth.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    /**
     *
     * 初始化 Token 方法
     *
     * @param Map<String, Object> claims JWT要附加的屬性
     * @param String      subject 識別主體值
     * @param String      jti Token 識別Id null 則隨機帶入UUDI
     *
     * @return String
     *
     */

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
