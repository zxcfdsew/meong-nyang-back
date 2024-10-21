package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.aspect.annotation.ValidAop;
import com.meongnyang.shop.dto.request.admin.ReqDeleteProductDto;
import com.meongnyang.shop.dto.request.admin.ReqModifyProductDto;
import com.meongnyang.shop.dto.request.admin.ReqRegisterProductDto;
import com.meongnyang.shop.dto.request.admin.ReqSearchDto;
import com.meongnyang.shop.service.admin.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @ValidAop
    @PostMapping("/product")
    public ResponseEntity<?> registerProduct(@Valid @ModelAttribute ReqRegisterProductDto dto, BindingResult bindingResult) {
        adminProductService.registerProduct(dto);

        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductsAll() throws IOException {
        return ResponseEntity.ok().body(adminProductService.getProductsAll());
    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductsByOption(@RequestBody ReqSearchDto dto) {
        return ResponseEntity.ok().body(adminProductService.getProductsByOption(dto));
    }


    @ValidAop
    @PutMapping("/product/{productId}")
    public ResponseEntity<?> modifyProduct(@Valid @ModelAttribute ReqModifyProductDto dto, BindingResult bindingResult) {
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