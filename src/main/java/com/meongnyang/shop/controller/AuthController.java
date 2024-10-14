package com.meongnyang.shop.controller;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.ReqAdminSigninDto;
import com.meongnyang.shop.dto.request.ReqOauth2SignupDto;
import com.meongnyang.shop.dto.request.ReqUserSigninDto;
import com.meongnyang.shop.dto.request.ReqUserSignupDto;
import com.meongnyang.shop.service.auth.AuthService;
import com.meongnyang.shop.service.admin.AdminAuthService;
import com.meongnyang.shop.service.auth.OAuth2Service;
import com.meongnyang.shop.service.user.UserService;
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
    private AuthService authService;

    @Autowired
    private OAuth2Service oAuth2Service;
    @Autowired
    private UserService userService;

    @ValidAop
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody ReqUserSignupDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        authService.signup(dto);
        return ResponseEntity.ok().body(null);
    }

    @ValidAop
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqUserSigninDto dto, BindingResult bindingResult) {
        System.out.println(dto);
        authService.signin(dto);
        return ResponseEntity.ok().body(null);
    }

    @ValidAop
    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oauth2Signup(@Valid @RequestBody ReqOauth2SignupDto dto, BindingResult bindingResult) {
        oAuth2Service.oauth2Signup(dto);
        return ResponseEntity.ok().body(null);
    }

    @ValidAop
    @PostMapping("/oauth2/signin")
    public ResponseEntity<?> oauth2Signin(@Valid @RequestBody ReqOauth2SignupDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(null);
    }

    @ValidAop
    @PostMapping("/admin/signin")
    public ResponseEntity<?> adminSignin(@Valid @RequestBody ReqAdminSigninDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(authService.adminSignin(dto));
    }



}
