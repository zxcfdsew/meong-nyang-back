package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.ReqDeleteProductDto;
import com.meongnyang.shop.dto.request.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.ReqRegisterProductDto;
import com.meongnyang.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/products")
    public ResponseEntity<?> getProductsAll() {
        return ResponseEntity.ok().body(productService.getProductsAll());
    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductsByOption(@RequestParam String option, @RequestParam String searchWord) {
        return ResponseEntity.ok().body(productService.getProductsByOption(option, searchWord));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> modifyProduct(@RequestBody ReqModifyProductDto dto) {
        productService.modifyProduct(dto);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteProducts(@RequestBody ReqDeleteProductDto dto) {
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/products/all")
    public ResponseEntity<?> deleteProductsAll() {
        productService.deleteProductsAll();
        return ResponseEntity.ok().body(null);
    }
}
