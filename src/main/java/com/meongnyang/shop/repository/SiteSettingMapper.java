package com.meongnyang.shop.repository;

import com.meongnyang.shop.entity.SiteSetting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SiteSettingMapper {

    SiteSetting getSiteSetting();
    int modifySiteSetting(SiteSetting siteSetting);

}
