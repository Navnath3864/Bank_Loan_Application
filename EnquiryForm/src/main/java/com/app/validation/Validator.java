package com.app.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.app.exception.InValidAgeException;
import com.app.exception.InValidEmailException;
import com.app.exception.InValidFirstNameException;
import com.app.exception.InValidLastNameException;
import com.app.exception.InValidMobileNoException;
import com.app.exception.InValidPancardNoException;
import com.app.model.EnquiryDetails;

@Component
public class Validator {

    public void validateFirstName(String firstName) 
    {
        for (char ch : firstName.toCharArray()) 
        {
            if (!Character.isAlphabetic(ch)) 
            {
                throw new InValidFirstNameException("First name must contain only letters.");
            }
        }
    }

    public void validateLastName(String lastName) 
    {
        for (char ch : lastName.toCharArray()) 
        {
            if (!Character.isAlphabetic(ch)) 
            {
                throw new InValidLastNameException("Last name must contain only letters.");
            }
        }
    }

    public void validateAge(int age) 
    {
        if (age <= 17) 
        {
            throw new InValidAgeException("Age is invalid, must be greater than 17.");
        }
    }

    public void validateEmail(String email) 
    {
        if (!email.endsWith("@gmail.com")) 
        {
            throw new InValidEmailException("Email must end with '@gmail.com'.");
        }
    }

    public void validateMobileNumber(long mobileNo) 
    {
        String mobileNumberString = String.valueOf(mobileNo);
        if (mobileNumberString.length() != 10) 
        {
            throw new InValidMobileNoException("Mobile number length must be exactly 10 digits.");
        }
    }

    public void validatePancardNo(String pancardNo) 
    {
        if (pancardNo == null || pancardNo.length() != 10) 
        {
            throw new InValidPancardNoException("PANCard number must have exactly 10 characters.");
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
            if (ch < '0' || ch > '9') 
            {
                throw new InValidPancardNoException("Middle 4 characters of the PANCard must be digits.");
            }
        }

       
        char lastChar = pancardNo.charAt(9);
        if (lastChar < 'A' || lastChar > 'Z') 
        {
            throw new InValidPancardNoException("The last character of the PANCard must be an uppercase letter.");
        }
    }
    
    public void validateEnquiryDetails(EnquiryDetails enquiryDetails) 
    {
        validateFirstName(enquiryDetails.getFirstName());
        validateLastName(enquiryDetails.getLastName());
        validateAge(enquiryDetails.getAge());
        validateEmail(enquiryDetails.getEmail());
        validateMobileNumber(enquiryDetails.getMobileNo());
        validatePancardNo(enquiryDetails.getPancardNo());
    }
	
}
