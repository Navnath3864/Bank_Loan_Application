package com.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.InValidAgeException;
import com.app.exception.InValidEmailException;
import com.app.exception.InValidFirstNameOrLastNameException;
import com.app.exception.InValidMobileNoException;
import com.app.exception.InValidPancardNoException;
import com.app.model.EnquiryDetails;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.EnquiryDetailsService;

@Service
public class EnquiryDetailsServiceImpl implements EnquiryDetailsService {
	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;
	

	@Override
<<<<<<< HEAD
	public EnquiryDetails saveDetails(EnquiryDetails enquiryDetails)  {
		if(!enquiryDetails.equals(enquiryDetails.getFirstName().toLowerCase()) && !enquiryDetails.equals(enquiryDetails.getLastName().toLowerCase()))
				{
					throw new InValidFirstNameOrLastNameException("first name or last name must be in lowercase");
				}
		String mobileno=String.valueOf(enquiryDetails.getMobileNo());
		if(mobileno.length()!=10)
		{
			throw new InValidMobileNoException ("mobile length is invalid ");
		}
		
		if(!enquiryDetails.getEmail().endsWith("@gmail.com"))
		{
			throw new InValidEmailException("email is invalid");
		}
		
		if(!(enquiryDetails.getAge()>=18))
		{
			throw new InValidAgeException("age is invalid");
		}
		
		String pancardNo=enquiryDetails.getPancardNo();
		if (pancardNo == null || pancardNo.length() != 10)
		{
            throw new InValidPancardNoException("PANCard number must have exactly 10 characters");
        }
 
        
        for (int i = 0; i < 5; i++)
        {
            char ch = pancardNo.charAt(i);
            if (ch < 'A' || ch > 'Z')
            {
                throw new InValidPancardNoException("The first 5 characters of the PANCard must be uppercase letters.");
            }
        }
 
       
        for (int i = 5; i < 9; i++)
        {
            char ch = pancardNo.charAt(i);
            if (ch < '0' || ch > '9') {
                throw new InValidPancardNoException("The next 4 characters of the PANCard must be digits.");
            }
        }
 
        
        char lastChar = pancardNo.charAt(9);
        if (lastChar < 'A' || lastChar > 'Z') {
            throw new InValidPancardNoException("The last character of the PAN must be an uppercase letter.");
        }
 
        
        System.out.println("PAN card is valid!");
       if(enquiryDetails.getLastName()!=null) {
    	   
       }

        return enquiryDetailsRepository.save(enquiryDetails);
=======
	public EnquiryDetails saveDetails(EnquiryDetails enquiryDetails) {
		return enquiryDetailsRepository.save(enquiryDetails);
>>>>>>> branch 'main' of https://github.com/Navnath3864/Bank_Loan_Application.git
	}

	
	@Override
    public List<EnquiryDetails> getAllEquiryDetails() {
		return enquiryDetailsRepository.findAll();
	}
	
	public EnquiryDetails getSingleEnquiryDetails(int customerID) {

		return enquiryDetailsRepository.findByCustomerID(customerID);
	}

	public void deleteEnquiryDetails(int customerID) {
		enquiryDetailsRepository.deleteById(customerID);
	}

	@Override
	public EnquiryDetails updateEnquiryDetails(EnquiryDetails enquiryDetails, int customerID) {
		EnquiryDetails enquiryDetails2 = enquiryDetailsRepository.findByCustomerID(customerID);
		if (enquiryDetails2!=null) {
			enquiryDetails2.setFirstName(enquiryDetails.getFirstName());
			enquiryDetails2.setLastName(enquiryDetails.getLastName());
			enquiryDetails2.setEmail(enquiryDetails.getEmail());
			enquiryDetails2.setAge(enquiryDetails.getAge());
			enquiryDetails2.setMobileNo(enquiryDetails.getMobileNo());
			enquiryDetails2.setPancardNo(enquiryDetails.getPancardNo());
			enquiryDetails2.setCibilScoreData(enquiryDetails.getCibilScoreData());
			enquiryDetailsRepository.save(enquiryDetails2);
			return enquiryDetails2;
		}
		return null;
	}
	
	
	

}
