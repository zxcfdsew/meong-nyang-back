package com.meongnyang.shop.init;

import com.meongnyang.shop.entity.Role;
import com.meongnyang.shop.entity.User;
import com.meongnyang.shop.entity.UserRole;
import com.meongnyang.shop.exception.JoinException;
import com.meongnyang.shop.repository.RoleMapper;
import com.meongnyang.shop.repository.UserMapper;
import com.meongnyang.shop.repository.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Set;

public class SampleData implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = JoinException.class)
    @Override
    public void run(String... args) throws Exception {
        try {
            User user = userMapper.findByUsername("admin");
            if (user == null) {
                user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234"))
                        .build();
                userMapper.save(user);

                Role role = roleMapper.findByRoleName("ROLE_ADMIN");
                if(role == null) {
                    role = Role.builder()
                            .roleName("ROLE_ADMIN")
                            .build();
                    roleMapper.save(role);
                }

                UserRole userRole = UserRole.builder()
                        .userId(user.getId())
                        .roleId(role.getId())
                        .build();
                userRoleMapper.save(userRole);

                user.setUserRoles(Set.of(userRole));
            }
        } catch (Exception e) {
            throw new JoinException(e.getMessage());
        }
    }
}
