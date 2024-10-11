package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.ReqAdminSigninDto;
import com.meongnyang.shop.service.admin.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @GetMapping("/admin/test")
    public ResponseEntity<?> adminTest() {
        System.out.println("관리자 요청");
        return ResponseEntity.ok().body(null);
    }
}
