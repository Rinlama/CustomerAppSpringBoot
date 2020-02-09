package com.crm.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public List<Customer> findAllByOrderByIdDesc();

}
