package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.response.admin.RespGetUserDetailDto;
import com.meongnyang.shop.dto.response.admin.RespGetUsersDto;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.NotFoundUserException;
import com.meongnyang.shop.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    @Autowired
    private UserMapper userMapper;

    public RespGetUsersDto getUsers() {

        List<User> userList = userMapper.findAll().stream()
                .filter(user -> user.getUserRoles().stream()
                        .anyMatch(role -> role.getRole().getRoleName().equals("ROLE_USER")))
                .collect(Collectors.toList());

        return RespGetUsersDto.builder()
                .userList(userList)
                .userListCount(userList.size())
                .build();
    }

    public RespGetUsersDto getUsersByOption(String option, String searchWord) {
        List<User> userList = userMapper.findUserByOption(option, searchWord).stream()
                .filter(user -> user.getUserRoles().stream()
                        .anyMatch(role -> role.getRole().getRoleName().equals("ROLE_USER")))
                .collect(Collectors.toList());

        return RespGetUsersDto.builder()
                .userList(userList)
                .userListCount(userList.size())
                .build();
    }

    public RespGetUserDetailDto getUserDetail(Long userId) {
        User user = userMapper.findUserDetailById(userId);
        if (user == null) {
            throw new NotFoundUserException("사용자 정보를 찾을 수 없습니다.");
        }
        return user.toDto();
    }
}
