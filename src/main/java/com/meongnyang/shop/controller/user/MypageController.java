package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.ReqUpdateUserDto;
import com.meongnyang.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MypageController {

    @Autowired
    private UserService userService;

    // 회원정보 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        System.out.println("회원정보 조회 완료");
        return ResponseEntity.ok().body(null);
    }

    // 회원정보 수정
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody ReqUpdateUserDto dto) {
        System.out.println("dto");
        return ResponseEntity.ok().body(userService.updateUser(dto));
    }


}
