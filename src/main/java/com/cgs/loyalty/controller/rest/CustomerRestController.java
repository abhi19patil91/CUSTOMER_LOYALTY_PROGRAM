package com.cgs.loyalty.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cgs.loyalty.dto.CustomerDto;
import com.cgs.loyalty.service.customer.LoyaltyCustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private LoyaltyCustomerService loyaltyCustomerService;

	// Register a customer into Loyalty program
	@PostMapping("/register")
	public ResponseEntity<CustomerDto> registerLoyaltyCustomer(@RequestBody CustomerDto customerDto) {
		
		CustomerDto savedCustomer = loyaltyCustomerService.save(customerDto);
		return new ResponseEntity<CustomerDto>(savedCustomer, HttpStatus.CREATED);
	}

	// Get Customers
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomer() {
		
		List<CustomerDto> savedCustomer = loyaltyCustomerService.getAllCustomer();
		return new ResponseEntity<List<CustomerDto>>(savedCustomer, HttpStatus.FOUND);
	}

	// Get Customer
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") String customerId) {
		
		CustomerDto customer = loyaltyCustomerService.getCustomer(customerId);
		return new ResponseEntity<CustomerDto>(customer, HttpStatus.FOUND);
	}

	// Update Customer
	@PutMapping("/update")
	public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
		
		CustomerDto savedCustomer = loyaltyCustomerService.addCustomer(customerDto);
		return new ResponseEntity<CustomerDto>(savedCustomer, HttpStatus.CREATED);
	}

	// Delete Customer
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable("customerId") String customerId) {
		
		loyaltyCustomerService.deleteById(customerId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
