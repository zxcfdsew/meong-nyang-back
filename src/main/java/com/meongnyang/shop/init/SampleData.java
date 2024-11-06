package com.meongnyang.shop.init;

import com.meongnyang.shop.entity.*;
import com.meongnyang.shop.exception.SignupException;
import com.meongnyang.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

//@Component
public class SampleData implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PetGroupMapper petGroupMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private MembershipMapper membershipMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        registerAdmin();
        registerCategory();
        registerMembership();
        registerPayment();
    }

    @Transactional(rollbackFor = SignupException.class)
    public void registerAdmin() {
        try {
            User user = userMapper.findUserByUsername("admin");
            if (user == null) {
                user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1234"))
                        .name("관리자")
                        .phone("010-1234-5678")
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
            throw new SignupException(e.getMessage());
        }
    }

    public void registerCategory() {
        try {
            PetGroup petGroup = petGroupMapper.findPetGroupByName("강아지");
            if(petGroup == null) {
                petGroupMapper.save( PetGroup.builder()
                        .categoryGroupName("강아지")
                        .build());
            }
            petGroup = petGroupMapper.findPetGroupByName("고양이");
            if(petGroup == null) {
                petGroupMapper.save(PetGroup.builder()
                        .categoryGroupName("고양이")
                        .build());
            }

            List<String> categorys = new ArrayList<>(Arrays.asList("간식/영양제", "미용/목욕", "위생/배변", "하네스/목줄", "장난감"));
            Category category = null;
            for(String categoryName : categorys) {
                category = categoryMapper.findCategoryByName(categoryName);
                if(category == null) {
                    categoryMapper.save(Category.builder()
                            .categoryName(categoryName)
                            .build());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registerMembership() {
        List<String> membershipList = new ArrayList<>(Arrays.asList("VIP", "일반", "블랙", "휴먼계정"));
        Membership membership = null;
        for(String membershipName : membershipList) {
            membership = membershipMapper.findMembershipByName(membershipName);
            if(membership == null) {
                membershipMapper.save(Membership.builder()
                        .membershipLevelName(membershipName)
                        .build());
            }
        }
    }

    public void registerPayment() {
        List<String> paymentList = new ArrayList<>(Arrays.asList("카드결제", "실시간 계좌이체", "휴대폰 결제"));
        List<String> paymentMethodList = new ArrayList<>(Arrays.asList("CARD", "TRANSFER", "MOBILE"));
//        List<String> paymentChannelKeyList = new ArrayList<>(Arrays.asList("CARD", "TRANSFER", "MOBILE", "VIRTUAL_ACCOUNT", "channel-key-74d2082b-6af1-4866-864d-1cc83fbf0917"));
        Payment payment = null;
        for(int i = 0; i < paymentList.size(); i++) {
            payment = paymentMapper.findPaymentByName(paymentList.get(i));
            if(payment == null) {
                paymentMapper.save(Payment.builder()
                        .paymentName(paymentList.get(i))
                        .paymentMethod(paymentMethodList.get(i))
                        .paymentChannelKey("channel-key-357b798b-239e-4b6e-ba4c-0f58b5faa3e4")
                        .build());
            }

        }
    }

}
