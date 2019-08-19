package com.example.demo.service;

import com.example.demo.domain.UserShipping;

import java.util.Optional;

public interface UserShippingService {

    UserShipping findById(Long id);

    void removeById(Long id);

}

