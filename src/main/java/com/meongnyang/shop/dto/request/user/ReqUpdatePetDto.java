package com.meongnyang.shop.dto.request.user;

import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.User;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class ReqUpdatePetDto {
    private Long userId;
    private String petName;
    private Long petAge;
    private String petType;



    public Pet toEntity() {
        return Pet.builder()
                .userId(userId)
                .petName(petName)
                .petAge(petAge)
                .petType(petType)
                .build();
    }
}
