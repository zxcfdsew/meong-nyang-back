package com.meongnyang.shop.service.auth;

import com.meongnyang.shop.dto.request.ReqAccessDto;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.AccessTokenException;
import com.meongnyang.shop.exception.NotFoundUserException;
import com.meongnyang.shop.repository.UserMapper;
import com.meongnyang.shop.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;

@Service
public class AccessService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProvider jwtProvider;

    public Boolean access(ReqAccessDto dto) {
        try {
            String token = jwtProvider.removeBearer(dto.getAccessToken());
            Claims claims = jwtProvider.getClaims(token);
            Long userId = ((Integer) claims.get("userId")).longValue();
            User user = userMapper.findById(userId);
            if(user == null) {
                throw new NotFoundUserException("사용자를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            throw new AccessTokenException(e.getMessage());
        }
        return true;
    }
}
