package com.meongnyang.shop.security.jwt;

import com.meongnyang.shop.entity.admin.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private Key key;

    private Key adminKey;

    public JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.admin}") String adminSecret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.adminKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(adminSecret));
    }

    public Date getExpireDate() {
        return new Date(new Date().getTime() + (1000L * 60 * 60 * 24 * 30));
    }

    //관리자 로그인 토큰 생성
    public String generateTokenByAdmin(Admin admin) {
        return Jwts.builder()
                .claim("adminId", admin.getId())
                .expiration(getExpireDate())
                .signWith(adminKey, SignatureAlgorithm.HS256)
                .compact();

    }

    public String removeBearer(String bearerAccessToken) {
        int bearerLength = "bearer ".length();
        if (bearerAccessToken == null) {
            throw new RuntimeException();
        }
        return bearerAccessToken.substring(bearerLength);
    }

}
