package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.admin.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.user.ReqGetOrderListDto;
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
        System.out.println(dto);
        orderService.postProductsOrder(dto);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("/user/orderlist")
    public ResponseEntity<?> getOrderList(ReqGetOrderListDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(orderService.getOrderList(dto));
    }
    @GetMapping("/user/orderlist/count")
    public ResponseEntity<?> getOrderListCount() {
        return ResponseEntity.ok().body(orderService.getOrderListCount());
    }
    // 취소요청
    @PutMapping("/order/status")
    public ResponseEntity<?> modifyProductsOrder(@RequestBody ReqModifyOrderDto dto) {
        System.out.println(dto.getUserId());
        orderService.modifyProductsOrder(dto);
        return ResponseEntity.ok().body(true);
    }
}
