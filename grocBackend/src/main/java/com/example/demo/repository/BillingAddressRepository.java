package com.example.demo.repository;

import com.example.demo.domain.BillingAddress;
import org.springframework.data.repository.CrudRepository;

public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long> {

}