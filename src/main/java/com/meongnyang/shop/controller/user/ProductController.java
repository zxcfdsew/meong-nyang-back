package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.user.ReqProductAllDto;
import com.meongnyang.shop.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/categorys")
    public ResponseEntity<?> getCategorys() {
        return ResponseEntity.ok().body(productService.getCategorys());
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductsAll(ReqProductAllDto dto) {
        System.out.println("요청");
        System.out.println(dto);
        return ResponseEntity.ok().body(productService.getProductsAll(dto));
    }

    @GetMapping("/products/count")
    public ResponseEntity<?> getProductsAllCount() {
        return ResponseEntity.ok().body(productService.getProductsCount());
    }
}
