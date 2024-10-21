package com.meongnyang.shop.dto.request;

import com.meongnyang.shop.entity.Address;
import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ReqUserSignupDto {
    @NotBlank
    @Size(min = 6, message = "사용자 이름은 6자 이상이어야 합니다.")
    private String username;

    @NotBlank
    @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
    private String password;

    @NotBlank
    private String checkPassword;

    @NotBlank
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 입력 가능합니다.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^(010-\\d{4}-\\d{4})$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phone;

    private Long zipcode;
    private String addressDefault;
    private String addressDetail;
    private String request;

    private String petName;
    private Long petAge;
    private String petType;

    public User toEntityByUser(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phone(phone)
                .build();
    }

    public Address toEntityByAddress(Long userId) {
        return Address.builder()
                .userId(userId)
                .zipcode(zipcode)
                .addressDefault(addressDefault)
                .addressDetail(addressDetail)
                .request(request)
                .build();
    }

    public Pet toEntityByPet(Long userId) {
        return Pet.builder()
                .userId(userId)
                .petName(petName)
                .petAge(petAge)
                .petType(petType)
                .build();
    }
}
