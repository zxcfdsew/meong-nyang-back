package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.admin.ReqModifyStockListDto;
import com.meongnyang.shop.dto.request.admin.ReqStockSearchOptionDto;
import com.meongnyang.shop.service.admin.AdminStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminStockController {

    @Autowired
    private AdminStockService adminStockService;

    @GetMapping("/products/stock")
    public ResponseEntity<?> getProductsStocks() {
        return ResponseEntity.ok().body(adminStockService.getStocks());
    }

    @GetMapping("/products/stock/search")
    public ResponseEntity<?> getProductsStockByOption(ReqStockSearchOptionDto dto) {
        return ResponseEntity.ok().body(adminStockService.getProductsStockByOption(dto));
    }

    @PutMapping("/products/stock")
    public ResponseEntity<?> modifyProductsStock(@RequestBody ReqModifyStockListDto dto) {
        adminStockService.modifyStock(dto);
        return ResponseEntity.ok().body(null);
    }
}