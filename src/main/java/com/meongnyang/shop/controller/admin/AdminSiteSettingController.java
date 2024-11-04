package com.meongnyang.shop.controller.admin;

import com.meongnyang.shop.dto.request.admin.ReqSiteSettingDto;
import com.meongnyang.shop.service.admin.AdminSiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminSiteSettingController {

    @Autowired
    private AdminSiteSettingService adminSiteSettingService;

    @GetMapping("/setting")
    public ResponseEntity<?> getSiteSetting() {
        return ResponseEntity.ok().body(adminSiteSettingService.getSiteSetting());
    }

    @PostMapping("/setting/logo")
    public ResponseEntity<?> changeLogo(@RequestParam("logo") MultipartFile logo) {
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/setting")
    public ResponseEntity<?> modifySiteSetting(@RequestBody ReqSiteSettingDto dto) {
        return ResponseEntity.ok().body(adminSiteSettingService.modifySiteSetting(dto));
    }
}