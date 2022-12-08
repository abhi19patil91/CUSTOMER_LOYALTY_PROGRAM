package com.cgs.loyalty.service.customer;

import java.util.List;
import com.cgs.loyalty.dto.CustomerDto;

public interface LoyaltyCustomerService {

	// Create Customer
	public CustomerDto save(CustomerDto customerDto);

	// Get Customers
	public List<CustomerDto> getAllCustomer();

	// Get Customer
	public CustomerDto getCustomer(String customerId);

	// Update Customer
	public CustomerDto addCustomer(CustomerDto customerDto);

	// Delete Customer
	public void deleteById(String customerId);

}
