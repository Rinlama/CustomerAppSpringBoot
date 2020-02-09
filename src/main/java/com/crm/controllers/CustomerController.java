package com.crm.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entity.Customer;
import com.crm.services.CustomerService;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	
	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public List<Customer> getCustomers(){
		return customerservice.getAllCustomer();
	}
	
	@RequestMapping(value="/customer/{id}",method=RequestMethod.GET)
	public Optional<Customer> getCustomerById(@PathVariable("id") String id){
		return customerservice.getCustomerById(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/customer",method=RequestMethod.POST)
	public Customer addCustomer(@Valid @RequestBody Customer customer){
		return customerservice.addCustomer(customer);
	}
	
	@RequestMapping(value="/customer/{id}",method=RequestMethod.PUT)
	public Optional<Customer> addCustomer(@PathVariable("id") String id,@Valid @RequestBody Customer customer){
		return customerservice.updateCustomer(Integer.parseInt(id), customer);
	}

	@RequestMapping(value="/customer/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("id") String id){
		 customerservice.deleteById(Integer.parseInt(id));
		 return ResponseEntity.ok().body("Customer " + id + " has been removed");
	}
	
	@RequestMapping(value="/customers/{ids}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteByIds(@PathVariable("ids") List<String> ids){
		 ids.forEach(d->{
			 if(customerservice.existByid(Integer.parseInt(d))){
				 customerservice.deleteById(Integer.parseInt(d));
			 }
		 });
		 return ResponseEntity.ok().body("Customers has been removed");
	}
	
	
}
