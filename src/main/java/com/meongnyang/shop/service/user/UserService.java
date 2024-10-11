package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.ReqUpdatePetDto;
import com.meongnyang.shop.dto.request.ReqUpdateUserDto;
import com.meongnyang.shop.dto.response.RespPetInfoDto;
import com.meongnyang.shop.dto.response.RespUserInfoDto;
import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.repository.PetMapper;
import com.meongnyang.shop.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PetMapper petMapper;

    // 회원정보 조회
    public RespUserInfoDto getUserInfo(Long id) {
        User user = userMapper.findById(id);

        Set<String> roles = user.getUserRoles().stream().map(
                userRole -> userRole.getRole().getRoleName()
        ).collect(Collectors.toSet());

        return RespUserInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .membershipLevelId(user.getMembershipLevelId())
                .roles(roles)
                .build();
    }

    // 회원정보 수정
    public void updateUser(ReqUpdateUserDto dto) {
        userMapper.updateUserById(dto.toEntity());
    }

    // 반려동물정보 조회
    public RespPetInfoDto getPetInfo(Long id) {
        Pet pet = petMapper.findPetById(id);

        return RespPetInfoDto.builder()
                .petId(pet.getId())
                .petName(pet.getPetName())
                .petAge(pet.getPetAge())
                .petType(pet.getPetType())
                .build();
    }

    public void updatePet(ReqUpdatePetDto dto) {
        petMapper.updatePetById(dto.toEntity());
    }
}
