package com.meongnyang.shop.dto.request.admin;

import com.meongnyang.shop.entity.SiteSetting;
import lombok.Data;

@Data
public class ReqSiteSettingDto {
    private String siteName;
    private String siteAddress;
    private String sitePhone;
    private int defaultDeliverCost;

    public SiteSetting toEntity() {
        return SiteSetting.builder()
                .siteName(siteName)
                .siteAddress(siteAddress)
                .sitePhone(sitePhone)
                .defaultDeliverCost(defaultDeliverCost)
                .build();
    }
}
