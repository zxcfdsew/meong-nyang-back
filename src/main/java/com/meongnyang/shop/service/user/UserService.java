package com.meongnyang.shop.service.user;

import com.meongnyang.shop.dto.request.ReqUpdateUserDto;
import com.meongnyang.shop.dto.response.RespUserInfoDto;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public RespUserInfoDto getUserInfo(Long id) {
        User user = userMapper.findById(id);

        Set<String> roles = user.getUserRoles().stream().map(
                userRole -> userRole.getRole().getRoleName()
        ).collect(Collectors.toSet());

        return RespUserInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public int updateUser(ReqUpdateUserDto dto) {
        return userMapper.updateUserById(dto.toEntity());
    }
}
