package com.example.demo.service.impl;

import com.example.demo.domain.UserShipping;
import com.example.demo.repository.UserShippingRepository;
import com.example.demo.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserShippingServiceImpl implements UserShippingService{

    @Autowired
    private UserShippingRepository userShippingRepository;

    public UserShipping findById(Long id) {
        return userShippingRepository.findById(id).orElse(null);
    }

    public void removeById(Long id) {
        userShippingRepository.deleteById(id);
    }
}

