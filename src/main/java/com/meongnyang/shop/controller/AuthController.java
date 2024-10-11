package com.meongnyang.shop.controller;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.ReqAdminSigninDto;
import com.meongnyang.shop.dto.request.ReqUserSigninDto;
import com.meongnyang.shop.dto.request.ReqUserSignupDto;
import com.meongnyang.shop.service.UserService;
import com.meongnyang.shop.service.admin.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminAuthService adminAuthService;

    @ValidAop
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody ReqUserSignupDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        userService.signup(dto);
        return ResponseEntity.ok().body(null);
    }

    @ValidAop
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqUserSigninDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        userService.signin(dto);
        return ResponseEntity.ok().body(null);
    }

    @ValidAop
    @PostMapping("/admin/signin")
    public ResponseEntity<?> adminSignin(@Valid @RequestBody ReqAdminSigninDto dto, BindingResult bindingResult) {
        System.out.println("관리자 로그인" + dto);
        return ResponseEntity.ok().body(adminAuthService.adminSignin(dto));
    }
}
