package com.example.demo.service.impl;

import com.example.demo.domain.UserPayment;
import com.example.demo.repository.UserPaymentRepository;
import com.example.demo.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;

    public UserPayment findById(Long id) {
        return userPaymentRepository.findById(id).orElse(null);
    }

    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }

}
