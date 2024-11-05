package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.admin.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.user.ReqModifyOrderDto;
import com.meongnyang.shop.dto.request.user.ReqPostOrderDto;
import com.meongnyang.shop.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<?> postProductsOrder(@RequestBody ReqPostOrderDto dto) {
        orderService.postProductsOrder(dto);
        return ResponseEntity.ok().body(true);
    }

//    @DeleteMapping("/order/{orderId}")
//    public ResponseEntity<?> deleteProductsOrder(@PathVariable Long orderId) {
//        orderService.deleteProductsOrder(orderId);
//        return ResponseEntity.ok().body(true);
//    }

//    @ValidAop
//    @PutMapping("/orders/{orderId}")
//    public ResponseEntity<?> modifyProductsOrder(@Valid @ModelAttribute ReqModifyOrderDto dto, BindingResult bindingResult) {
//        orderService.modifyProductsOrder(dto);
//        return ResponseEntity.ok().body(true);
//    }
}
