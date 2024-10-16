package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.admin.ReqDeleteProductDto;
import com.meongnyang.shop.dto.request.admin.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.admin.ReqRegisterProductDto;
import com.meongnyang.shop.service.admin.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @PostMapping("/product")
    public ResponseEntity<?> registerProduct(@ModelAttribute ReqRegisterProductDto dto) {
        adminProductService.registerProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductsAll() throws IOException {
        return ResponseEntity.ok().body(adminProductService.getProductsAll());
    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductsByOption(@RequestParam String option, @RequestParam String searchWord) {
        return ResponseEntity.ok().body(adminProductService.getProductsByOption(option, searchWord));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> modifyProduct(@ModelAttribute ReqModifyProductDto dto) {
        System.out.println(dto);
        adminProductService.modifyProduct(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteProducts(@RequestBody ReqDeleteProductDto dto) {
        adminProductService.deleteProductById(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/products/all")
    public ResponseEntity<?> deleteProductsAll() {
        adminProductService.deleteProductsAll();
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/categorys")
    public ResponseEntity<?> getCategorys() {
        return ResponseEntity.ok().body(adminProductService.getCategorys());
    }
}