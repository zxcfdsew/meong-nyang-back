package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.admin.ReqModifyMembershipLevelDto;
import com.meongnyang.shop.dto.request.admin.ReqSearchDto;
import com.meongnyang.shop.dto.response.admin.RespGetMembershipsDto;
import com.meongnyang.shop.dto.response.admin.RespGetUserDetailDto;
import com.meongnyang.shop.dto.response.admin.RespGetUsersDto;
import com.meongnyang.shop.entity.Pet;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.entity.UserOrder;
import com.meongnyang.shop.exception.NotFoundMembershipException;
import com.meongnyang.shop.exception.NotFoundUserException;
import com.meongnyang.shop.repository.MembershipMapper;
import com.meongnyang.shop.repository.OrderMapper;
import com.meongnyang.shop.repository.PetMapper;
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
    @Autowired
    private MembershipMapper membershipMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PetMapper petMapper;

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
        System.out.println("tlfgod");
        User user = userMapper.findUserDetailById(userId);
        System.out.println(user);
        List<UserOrder> userOrderList = orderMapper.findOrderById(userId);
        Pet pet = petMapper.findPetByUserId(userId);

        System.out.println(user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName()).equals("ROLE_ADMIN"));
        if (user == null) {
            throw new NotFoundUserException("사용자를 찾을 수 없습니다.");
        }

        return user.toDto(userOrderList, pet);
    }

    public void modifyUserMembership(ReqModifyMembershipLevelDto dto) {
        User user = userMapper.findUserById(dto.getUserId());
        if (user == null) {
            throw new NotFoundUserException("사용자를 찾을 수 없습니다.");
        }

        List<Long> membershipList = membershipMapper.findMembershipId();
        if (!membershipList.contains(dto.getMembershipId())) {
            throw new NotFoundMembershipException("존재하지 않는 등급입니다.");
        }
        userMapper.updateUserMembershipById(dto.getUserId(), dto.getMembershipId());
    }

    public RespGetMembershipsDto getMemberships() {
        return RespGetMembershipsDto.builder()
                .membershipList(membershipMapper.findMembershipAll())
                .build();
    }
}
