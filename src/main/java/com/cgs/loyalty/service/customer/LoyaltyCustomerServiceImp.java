package com.cgs.loyalty.service.customer;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cgs.loyalty.dto.CustomerDto;
import com.cgs.loyalty.entity.customer.LoyaltyCustomerDetails;
import com.cgs.loyalty.repository.LoyaltyCustomerRepository;
import com.cgs.loyalty.util.CustomerValidator;

@Service
public class LoyaltyCustomerServiceImp implements LoyaltyCustomerService {

	@Autowired
	private LoyaltyCustomerRepository customerLoyaltyRepository;

	@Autowired
	private CustomerValidator customerValidator;

	@Autowired
	private ModelMapper modelMapper;

	// Save customer
	@Override
	public CustomerDto save(CustomerDto customerDto) {

		CustomerDto customerDto1 = customerValidator.validateToCreateCustomerRequest(customerDto);

		LoyaltyCustomerDetails customer = dtoToLoyCustomerDetails(customerDto1);
		LoyaltyCustomerDetails savedCustomer = customerLoyaltyRepository.save(customer);
		return loyCustomerDetailsToDto(savedCustomer);
	}

	// get all customer
	@Override
	public List<CustomerDto> getAllCustomer() {

		List<LoyaltyCustomerDetails> customers = customerValidator.checkListEmpty();
		List<CustomerDto> dtoCustomers = customers.stream().map(customer -> loyCustomerDetailsToDto(customer))
				.collect(Collectors.toList());
		return dtoCustomers;
	}

	// get customer
	@Override
	public CustomerDto getCustomer(String customerId) {

		LoyaltyCustomerDetails customer = customerValidator.checkCustomerPresentOrNot(customerId);
		return loyCustomerDetailsToDto(customer);
	}

	// Update customer
	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {

		LoyaltyCustomerDetails customer = customerValidator.checkCustomerPresentOrNot(customerDto);

		customer.setName(customerDto.getName());
		customer.setMobileNo(customerDto.getMobileNo());
		customer.setEmail(customerDto.getEmail());
		customer.setDob(customerDto.getDob());
		customer.setCustomerType(customerDto.getCustomerType());
		customer.setRating(customerDto.getRating());
		customer.setChannelOfRegistration(customerDto.getChannelOfRegistration());

		LoyaltyCustomerDetails customer1 = customerLoyaltyRepository.save(customer);
		return loyCustomerDetailsToDto(customer1);
	}

	// delete customer
	@Override
	public void deleteById(String customerId) {

		LoyaltyCustomerDetails customer = customerValidator.checkCustomerPresentOrNot(customerId);
		customerLoyaltyRepository.deleteById(customer.getCustomerId());

	}

	// Dto to LoyaltyCustomerDetails
	public LoyaltyCustomerDetails dtoToLoyCustomerDetails(CustomerDto customerDto) {

		LoyaltyCustomerDetails customerDetails = this.modelMapper.map(customerDto, LoyaltyCustomerDetails.class);
		return customerDetails;
	}

	// LoyaltyCustomerDetails to Dto
	public CustomerDto loyCustomerDetailsToDto(LoyaltyCustomerDetails loyCustomerDetails) {

		CustomerDto customerDto = this.modelMapper.map(loyCustomerDetails, CustomerDto.class);
		return customerDto;
	}

}
