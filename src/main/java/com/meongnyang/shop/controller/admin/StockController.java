package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.admin.ReqModifyStockListDto;
import com.meongnyang.shop.entity.Stock;
import com.meongnyang.shop.service.admin.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/products/stock")
    public ResponseEntity<?> getProductsStocks() {
        return ResponseEntity.ok().body(stockService.getStocks());
    }

    @GetMapping("/products/stock/search")
    public ResponseEntity<?> getProductsStockByOption(@RequestParam String option, @RequestParam String searchWord) {
        return ResponseEntity.ok().body(stockService.getProductsStockByOption(option, searchWord));
    }

    @PutMapping("/products/stock")
    public ResponseEntity<?> modifyProductsStock(@RequestBody ReqModifyStockListDto dto) {
        stockService.modifyStock(dto);
        return ResponseEntity.ok().body(null);
    }
}
