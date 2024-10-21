package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.ReqGetCartDto;
import com.meongnyang.shop.dto.request.ReqPostCartDto;
import com.meongnyang.shop.service.user.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    UserCartService userCartService;

    // 장바구니 물품 추가
    @PostMapping("/user/cart")
    public ResponseEntity<?> postCart(@RequestBody ReqPostCartDto dto) {
        userCartService.saveProductCart(dto);
        return ResponseEntity.ok().body(true);
    }

    // 장바구니 내역 조회
    @GetMapping("/user/cart")
    public ResponseEntity<?> getCart(ReqGetCartDto dto) {
        return ResponseEntity.ok().body(userCartService.getCart(dto));
    }

    // 장바구니 내역 삭제
    @DeleteMapping("/user/cart")
    public ResponseEntity<?> deleteCartAll(@RequestBody List<Integer> cartIds) {
        userCartService.deleteCart(cartIds);
        return ResponseEntity.ok().body(true);
    }
}
