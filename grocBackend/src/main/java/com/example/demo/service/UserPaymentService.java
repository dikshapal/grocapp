package com.example.demo.service;

import com.example.demo.domain.UserPayment;

import java.util.Optional;

public interface UserPaymentService {
    UserPayment findById(Long id);

    void removeById(Long id);
}
