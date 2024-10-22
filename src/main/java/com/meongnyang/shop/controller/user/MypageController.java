package com.meongnyang.shop.controller.user;

import com.meongnyang.shop.dto.request.ReqGetUserOrderDto;
import com.meongnyang.shop.dto.request.ReqUpdatePetDto;
import com.meongnyang.shop.dto.request.ReqUpdateUserDto;
import com.meongnyang.shop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MypageController {

    @Autowired
    private UserService userService;

    // 회원정보 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        System.out.println("회원정보 조회 완료");
        return ResponseEntity.ok().body(userService.
                getUserInfo(id));
    }

    // 회원정보 수정
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody ReqUpdateUserDto dto) {
        userService.updateUser(dto);
        return ResponseEntity.ok().body(true);
    }

    // 반려동물정보 조회

    @GetMapping("/user/pet/{userId}")
    public ResponseEntity<?> getPet(@PathVariable Long userId) {
        System.out.println("반려동물정보 조회 완료");
        return ResponseEntity.ok().body(userService.getPetInfo(userId));
    }

    // 반려동물정보 수정
    @PutMapping("/user/pet/{petId}")
    public ResponseEntity<?> updatePet(@RequestBody ReqUpdatePetDto dto, @PathVariable Long petId) {
        userService.updatePet(dto);
        System.out.println(dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body(true);
    }
    // 주문/배송 내역 조회
    @GetMapping("/product/orders")
    public ResponseEntity<?> getUserOrder(ReqGetUserOrderDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok(userService.getUserOrders(dto));
    }

}
