package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.service.admin.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrdersAll() {

        return ResponseEntity.ok().body(adminOrderService.getOrders());
    }
}
