package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.user.ReqGetCartDto;
import com.meongnyang.shop.service.user.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    UserCartService userCartService;

    // 장바구니 내역 조회
    @GetMapping("/user/cart")
    public ResponseEntity<?> getCart(ReqGetCartDto dto) {
        return ResponseEntity.ok().body(userCartService.getCart(dto));
    }
}
