package com.cgs.loyalty.dto;

import javax.persistence.Id;
import lombok.Data;

@Data
public class CustomerDto {
	
	@Id
	private String customerId;
	private String name;
	private String mobileNo;
	private String email;
	private String dob;
	private String customerType;
	private String rating;
	private String channelOfRegistration;

}
