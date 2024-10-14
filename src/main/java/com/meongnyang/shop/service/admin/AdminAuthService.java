package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.ReqAdminSigninDto;
import com.meongnyang.shop.dto.response.RespAdminSigninDto;
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

}
