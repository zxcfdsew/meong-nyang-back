package com.meongnyang.shop.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class StockController {

    @GetMapping("/stocks")
    public ResponseEntity<?> getStocks() {
        return ResponseEntity.ok().body(null);
    }
}
