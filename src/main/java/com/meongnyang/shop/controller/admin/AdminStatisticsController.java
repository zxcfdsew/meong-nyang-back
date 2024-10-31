package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.admin.ReqStatisticsDateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminStatisticsController {

    @GetMapping("/statistics")
    public ResponseEntity<?> getStatistics(ReqStatisticsDateDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(null);
    }
}