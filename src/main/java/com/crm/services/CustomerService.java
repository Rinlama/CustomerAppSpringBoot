package com.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.Customer;
import com.crm.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerrepository;
	
	public List<Customer> getAllCustomer(){
		return customerrepository.findAllByOrderByIdDesc();
	}
	public Customer addCustomer(Customer customer){
		return customerrepository.save(customer);
	}
	public Boolean existByid(Integer id){
		return customerrepository.existsById(id);
	}
	public Optional<Customer> getCustomerById(Integer id){
		return customerrepository.findById(id);
	}
	public Optional<Customer> updateCustomer(Integer id,Customer customer){
		return customerrepository.findById(id).map((d)->{
				d.setName(customer.getName());
				d.setDob(customer.getDob());
				d.setCreditlimit(customer.getCreditlimit());
			return customerrepository.save(d);
		});
	}
	
	public void deleteById(Integer id){
		 customerrepository.deleteById(id);
	}

}
