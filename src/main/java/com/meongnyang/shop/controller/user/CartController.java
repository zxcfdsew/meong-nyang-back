package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.admin.ReqDeleteProductDto;
import com.meongnyang.shop.dto.request.user.ReqDeleteCartDto;
import com.meongnyang.shop.dto.request.user.ReqGetCartDto;
import com.meongnyang.shop.dto.request.user.ReqPostCartDto;
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
    public ResponseEntity<?> saveProductCart(@RequestBody ReqPostCartDto dto) {
        userCartService.saveCart(dto);
        return ResponseEntity.ok().body(true);
    }

    // 장바구니 내역 조회
    @GetMapping("/user/cart")
    public ResponseEntity<?> getCart(ReqGetCartDto dto) {
        return ResponseEntity.ok().body(userCartService.getCart(dto));
    }

    // 장바구니 내역 전체 삭제
    @DeleteMapping("/user/cart/all")
    public ResponseEntity<?> deleteCartAll(@RequestBody List<Long> userIds) {
        userCartService.deleteCartAll(userIds);
        return ResponseEntity.ok().body(true);
    }

    // 장바구니 내역 선택 삭제
    @DeleteMapping("/user/cart")
    public ResponseEntity<?> deleteCart(ReqDeleteCartDto dto) {
        userCartService.deleteCart(dto);
        return ResponseEntity.ok().body(true);
    }
}
