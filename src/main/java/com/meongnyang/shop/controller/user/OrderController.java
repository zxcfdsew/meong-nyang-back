package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.user.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.user.ReqPostOrderDto;
import com.meongnyang.shop.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 주문 등록
    @PostMapping("/order")
    public ResponseEntity<?> postProductsOrder(@RequestBody ReqPostOrderDto dto) {
        orderService.postProductsOrder(dto);
        return ResponseEntity.ok().body(true);
    }

    // 취소요청
    @PutMapping("/orders")
    public ResponseEntity<?> modifyProductsOrder(ReqModifyOrderDto dto) {
        orderService.modifyProductsOrder(dto);
        return ResponseEntity.ok().body(true);
    }
}