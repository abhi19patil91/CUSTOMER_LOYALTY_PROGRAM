package com.cgs.loyalty.util;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cgs.loyalty.dto.CustomerDto;
import com.cgs.loyalty.entity.customer.LoyaltyCustomerDetails;
import com.cgs.loyalty.exception.CustomerNotExistException;
import com.cgs.loyalty.exception.IdAlreadyExistException;
import com.cgs.loyalty.exception.InvalidChannelOfRegistrationException;
import com.cgs.loyalty.exception.InvalidCustomerTypeException;
import com.cgs.loyalty.exception.InvalidDobException;
import com.cgs.loyalty.exception.InvalidEmailException;
import com.cgs.loyalty.exception.InvalidIdException;
import com.cgs.loyalty.exception.InvalidMobileNoException;
import com.cgs.loyalty.exception.InvalidNameException;
import com.cgs.loyalty.exception.InvalidRatingException;
import com.cgs.loyalty.exception.ListIsEmptyException;
import com.cgs.loyalty.repository.LoyaltyCustomerRepository;

@Component
public class CustomerValidator {

	@Autowired
	LoyaltyCustomerRepository loyaltyCustomerRepository;
	
//	Validate customer Attributes
	public CustomerDto validateToCreateCustomerRequest(CustomerDto customerDto) {

		Optional<LoyaltyCustomerDetails> customer = loyaltyCustomerRepository.findById(customerDto.getCustomerId());

		if (customer.isEmpty()) {

			String regaxId = "^[\\d]{4}$";
			String regaxEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
			String regaxDob = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
			String regaxRating = "^[1-5]$";
			boolean id = customerDto.getCustomerId().matches(regaxId);
			boolean email = customerDto.getEmail().matches(regaxEmail);
			boolean dob = customerDto.getDob().matches(regaxDob);
			boolean rating = customerDto.getRating().matches(regaxRating);

			//validate Name
			if (id == false) {
				throw new InvalidIdException();
			}

			// Email
			if (email == false) {
				throw new InvalidEmailException();
			}

			// DateOfBirth
			if (dob == false) {
				throw new InvalidDobException();
			}

			// MobileNo
			if (customerDto.getMobileNo().length() != 10) {
				throw new InvalidMobileNoException();
			}

			// Rating
			if (rating == false) {
				throw new InvalidRatingException();
			}

			// Name
			if (customerDto.getName().isEmpty() || customerDto.getName().length() == 0) {
				throw new InvalidNameException();
			}

			// CustomerType
			if (customerDto.getCustomerType().isEmpty() || customerDto.getCustomerType().length() == 0) {
				throw new InvalidCustomerTypeException();
			}

			// ChannelOfRegistration
			if (customerDto.getChannelOfRegistration().isEmpty()
					|| customerDto.getChannelOfRegistration().length() == 0) {
				throw new InvalidChannelOfRegistrationException();
			}

			return customerDto;

		} else {
			throw new IdAlreadyExistException();
		}
	}

//	Validate the List is empty or not
	public List<LoyaltyCustomerDetails> checkListEmpty() {

		List<LoyaltyCustomerDetails> customers = loyaltyCustomerRepository.findAll();
		if (customers.isEmpty()) {
			throw new ListIsEmptyException();
		}
		return customers;
	}
	
//	validate the customerId present in data base or not
	public LoyaltyCustomerDetails checkCustomerPresentOrNot(String customerId) {
		
		LoyaltyCustomerDetails customer = loyaltyCustomerRepository.findById(customerId).orElse(null);
		if (customer==null) {
			throw new CustomerNotExistException();
		}
		return customer;
	}
	
//	validate the customer present in data base or not
	public LoyaltyCustomerDetails checkCustomerPresentOrNot(CustomerDto customerDto) {
		
		LoyaltyCustomerDetails customer = loyaltyCustomerRepository.findById(customerDto.getCustomerId()).orElse(null);
		if (customer==null) {
			throw new CustomerNotExistException();
		}
		return customer;
	}

}
