package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.admin.ReqSearchDto;
import com.meongnyang.shop.dto.response.admin.RespGetUserDetailDto;
import com.meongnyang.shop.dto.response.admin.RespGetUsersDto;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.exception.NotFoundUserException;
import com.meongnyang.shop.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public RespGetUsersDto getUsersByOption(ReqSearchDto dto) {
        Long startIndex = (dto.getPage() - 1) * dto.getLimit();

        Map<String, Object> params = Map.of(
                "startIndex", startIndex,
                "limit", dto.getLimit(),
                "searchWord", dto.getSearch() == null ? "" : dto.getSearch(),
                "option", dto.getOption() == null || dto.getOption().isBlank() ? "all" : dto.getOption()
        );

        List<User> userList = userMapper.findUserByOption(params).stream()
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
            throw new NotFoundUserException("사용자를 찾을 수 없습니다.");
        }
        return user.toDto();
    }

    public void modifyUserMembership() {
        User user = userMapper.findUserDetailById(1L);
    }
}
