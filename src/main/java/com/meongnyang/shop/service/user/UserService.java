package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.response.user.RespUserInfoDto;
import com.meongnyang.shop.entity.Address;
import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.repository.user.MyPageMapper;
import com.meongnyang.shop.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private MyPageMapper myPageMapper;

    public RespUserInfoDto getUserInfo(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication.getPrincipal() instanceof PrincipalUser)) {
//            throw new AuthenticationServiceException("유효하지 않은 사용자입니다.");
//        }
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        User user = myPageMapper.findById(id);
        if(principalUser.getId() != id) {
            throw new AuthenticationServiceException("권한이없습니다.");
        }
        if(user == null) {
            throw new AuthenticationServiceException("해당 사용자는 존재하지 않는 사용자입니다.");
        }
        Address address = user.getAddress();
        Pet pet = user.getPet();
        System.out.println(user);
//        if (address == null) {
//            throw new IllegalStateException("주소 정보가 없습니다.");
//        }
//        if (pet == null) {
//            throw new IllegalStateException("반려동물 정보가 없습니다.");
//        }

        Set<String> roles = user.getUserRoles().stream().map(
                userRole -> userRole.getRole().getRoleName()
        ).collect(Collectors.toSet());

        return RespUserInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .phone(user.getPhone())
                .addressId(address.getId())
                .zipcode(address.getZipcode())
                .addressDefault(address.getAddressDefault())
                .addressDetail(address.getAddressDetail())
                .petId(pet.getId())
                .petName(pet.getPetName())
                .petAge(pet.getPetAge())
                .petType(pet.getPetType())
                .roles(roles)
                .build();
    }
}
