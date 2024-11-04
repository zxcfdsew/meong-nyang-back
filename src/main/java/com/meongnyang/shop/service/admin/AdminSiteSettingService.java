package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.admin.ReqSiteSettingDto;
import com.meongnyang.shop.dto.response.admin.RespSiteSettingDto;
import com.meongnyang.shop.entity.ImgUrl;
import com.meongnyang.shop.exception.ModifyFailedException;
import com.meongnyang.shop.repository.SiteSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AdminSiteSettingService {

    @Autowired
    private SiteSettingMapper siteSettingMapper;

    @Value("${file.path}")
    private String filePath;

    public RespSiteSettingDto getSiteSetting() {
        return siteSettingMapper.getSiteSetting().toDto();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean modifySiteSetting(ReqSiteSettingDto dto) {
        int result = siteSettingMapper.modifySiteSetting(dto.toEntity());
        if (result < 1) {
            throw new ModifyFailedException("수정 실패!");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean modifySiteLogo(MultipartFile logo) throws IOException {
        String logoName = logo.getOriginalFilename();
        File directory = new File(filePath);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(filePath + logoName);
        logo.transferTo(file);


        return true;
    }
}
