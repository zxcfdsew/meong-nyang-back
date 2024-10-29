package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.user.ReqUpdatePasswordDto;
import com.meongnyang.shop.dto.request.user.ReqUpdatePetDto;
import com.meongnyang.shop.dto.request.user.ReqUpdateUserDto;
import com.meongnyang.shop.dto.response.user.RespUserInfoDto;
import com.meongnyang.shop.entity.Address;
import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.Product;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.SignupException;
import com.meongnyang.shop.exception.UpdateUserException;
import com.meongnyang.shop.exception.ValidException;
import com.meongnyang.shop.repository.user.MyPageMapper;
import com.meongnyang.shop.repository.user.UserAddressMapper;
import com.meongnyang.shop.repository.user.UserPetMapper;
import com.meongnyang.shop.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private MyPageMapper myPageMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private UserPetMapper userPetMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return myPageMapper.findUserByUsername(authentication.getName());
    }

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

        Set<String> roles = user.getUserRoles().stream().map(
                userRole -> userRole.getRole().getRoleName()
        ).collect(Collectors.toSet());

        return RespUserInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .phone(user.getPhone())
                .addressId(address != null ? address.getId() : null)
                .zipcode(address != null ? address.getZipcode().toString() : "")
                .addressDefault(address != null ? address.getAddressDefault() : "")
                .addressDetail(address != null ? address.getAddressDetail() : "")
                .petId(pet != null ? pet.getId() : null)
                .petName(pet != null ? pet.getPetName() : "")
                .petAge(pet != null ? pet.getPetAge().toString() : "")
                .petType(pet != null ? pet.getPetType() : "")
                .roles(roles)
                .build();
    }

    @Transactional(rollbackFor = UpdateUserException.class)
    public void updateUser(ReqUpdateUserDto dto) {
        try {
            User user = getCurrentUser();
            Address address = getCurrentUser().getAddress();

            user.setName(dto.toEntity().getName());
            user.setPhone(dto.toEntity().getPhone());
            address.setUserId(dto.toEntityAddress().getId());
            address.setZipcode(dto.toEntityAddress().getZipcode());
            address.setAddressDefault(dto.toEntityAddress().getAddressDefault());
            address.setAddressDetail(dto.toEntityAddress().getAddressDetail());
            myPageMapper.UpdateUserInfoById(user);

            if (userAddressMapper.findAddressByUserId(user.getId()) == null) {
                address = new Address();
                userAddressMapper.saveAddress(address);
            } else {
                userAddressMapper.UpdateAddressByUserId(address);
            }
        } catch (Exception e) {
            throw new UpdateUserException(e.getMessage());
        }
    }

    public void editPassword(ReqUpdatePasswordDto dto) {
        User user = getCurrentUser();

        if(!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new ValidException(Map.of("oldPassword", "비밀번호 인증에 실패하였습니다. 다시 입력하세요"));
        }
        if(!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            throw new ValidException(Map.of("newPasswordCheck", "새로운 비밀번호가 서로 일치하지 않습니다. 다시 입력하세요"));
        }
        if(passwordEncoder.matches(dto.getNewPassword(), user.getPassword())) {
            throw new ValidException(Map.of("newPasswordCheck", "이전 비밀번호와 동일한 비밀번호는 사용하실 수 없습니다. 다시 입력하세요"));
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        myPageMapper.editPassword(user);
    }

    public void modifyPet(ReqUpdatePetDto dto) {
        try {
            Pet pet = getCurrentUser().getPet();
            pet.setUserId(dto.toEntity().getUserId());
            pet.setPetName(dto.toEntity().getPetName());
            pet.setPetAge(dto.toEntity().getPetAge());
            pet.setPetType(dto.toEntity().getPetType());

            if(userPetMapper.UpdatePetByUserId(dto.toEntity()) == null) {
                pet = new Pet();
                userPetMapper.savePet(pet);
            } else {
                userPetMapper.UpdatePetByUserId(pet);
            }
        } catch (Exception e) {
            throw new UpdateUserException(e.getMessage());
        }
    }
}
