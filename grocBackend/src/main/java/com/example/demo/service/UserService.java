package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserBilling;
import com.example.demo.domain.UserPayment;
import com.example.demo.domain.UserShipping;
import com.example.demo.domain.security.UserRole;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    User createUser(User user, Set<UserRole> userRoles);

    User findByUsername(String username);

    User findByEmail (String email);

    User save(User user);

    User findById(Long id);

    void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultShipping(Long userShippingId, User user);

}
