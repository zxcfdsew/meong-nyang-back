package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.ReqAdminSigninDto;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.repository.RoleMapper;
import com.meongnyang.shop.repository.UserMapper;
import com.meongnyang.shop.repository.UserRoleMapper;
import com.meongnyang.shop.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void adminSignin(ReqAdminSigninDto dto) {
        User user = userMapper.findByUsername(dto.getUsername());
        if(user == null) {
            throw new UsernameNotFoundException("관리자 정보를 확인하세요");
        }
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("관리자 정보를 확인하세요");
        }
    }
//    public String generateToken(ReqAdminSigninDto dto) {
//
//        return jwtProvider.generateTokenByAdmin(dto.toEntity());
//    }
}
