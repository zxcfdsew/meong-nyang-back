package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.ReqRegisterProductDto;
import com.meongnyang.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> registerProduct(@RequestBody ReqRegisterProductDto dto) {
        productService.registerProduct(dto);
        return ResponseEntity.ok().body(null);
    }
}
