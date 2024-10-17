package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.ReqPostOrderDto;
import com.meongnyang.shop.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 주문 등록
    @PostMapping("/order")
    public ResponseEntity<?> postProductsOrder(@RequestBody List<ReqPostOrderDto> dto) {
        orderService.postProductsOrder(dto);
        return ResponseEntity.ok(true);
    }

    // 주문 삭제
    @DeleteMapping("/user/orders")
    public ResponseEntity<?> deleteProductsOrder(@RequestBody List<Integer> orderId) {
        orderService.deleteProductsOrder(orderId);
        return ResponseEntity.ok(true);
    }

    @ValidAop
    @PutMapping("/user/order")
    public ResponseEntity<?> modifyProductsOrder(@Valid @RequestBody ReqModifyOrderDto dto, BindingResult bindingResult) {
        orderService.modifyProductsOrder(dto);
        return ResponseEntity.ok(true);
    }
}
