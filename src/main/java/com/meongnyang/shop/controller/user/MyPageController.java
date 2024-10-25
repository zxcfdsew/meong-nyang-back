package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.security.principal.PrincipalUser;
import com.meongnyang.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserMe(@PathVariable Long userId) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok().body(userService.getUserInfo(userId));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUserMe(@PathVariable Long userId) {
        return ResponseEntity.ok().body(null);
    }

}
