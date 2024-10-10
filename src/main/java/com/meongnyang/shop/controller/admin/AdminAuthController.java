package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.ReqAdminSigninDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @ValidAop
    @PostMapping("/auth/signin")
    public ResponseEntity<?> adminSignin(@Valid @RequestBody ReqAdminSigninDto dto, BindingResult bindingResult) {
        System.out.println("관리자 로그인" + dto);
        return ResponseEntity.ok().body(null);
    }


}
