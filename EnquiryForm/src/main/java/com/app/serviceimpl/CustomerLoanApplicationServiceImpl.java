
package com.app.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.exceptions.HandleCustomException;
import com.app.model.AllPersonalDocs;
import com.app.model.CustomerLoanApplication;
import com.app.model.EnquiryDetails;
import com.app.repository.CustomerLoanApplicationRepository;
import com.app.repository.EnquiryDetailsRepository;
import com.app.service.CustomerLoanApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerLoanApplicationServiceImpl implements CustomerLoanApplicationService {

	@Autowired
	CustomerLoanApplicationRepository customerLoanApplicationRepository;

	@Autowired
	EnquiryDetailsRepository enquiryDetailsRepository;

	@Override
	public CustomerLoanApplication saveDetails(String customerLoanApplication1, int id, MultipartFile addressProof,
			MultipartFile panCard, MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo,

			MultipartFile signature, MultipartFile bankCheque, MultipartFile salarySlips) {

		CustomerLoanApplication customerLoanApplication = null;

		EnquiryDetails details = enquiryDetailsRepository.findByCustomerID(id);
		
		ObjectMapper objectMapper = new ObjectMapper();

		try {

			customerLoanApplication = objectMapper.readValue(customerLoanApplication1, CustomerLoanApplication.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();

		} catch (JsonProcessingException e) {

			e.printStackTrace();

		}
		/*******************************************************************************************************************/
		if (customerLoanApplication.getRequiredTenure() < 1) {
			throw new HandleCustomException("Tenure must be at least 1 year");
		} else if (customerLoanApplication.getRequiredTenure() > 50) {
			throw new HandleCustomException("Tenure cannot exceed 50 years");
		}
		if (!customerLoanApplication.getCustomerGender().matches("Male|Female|male|female|other")) {
			throw new HandleCustomException(
					"Customer gender must be one of the following:Male|Female|male|female|other");
		}
		if (customerLoanApplication.getCustomerAdditionalMobileNumber() < 1000000000
				|| customerLoanApplication.getCustomerAdditionalMobileNumber() > 9999999999L) {
			throw new HandleCustomException("Mobile number must be at least 10 digits");
		}
		if (customerLoanApplication.getCustomerAmountPaidForHome() < 1
				|| customerLoanApplication.getCustomerAmountPaidForHome() > 1000000000.00) {
			throw new HandleCustomException("Customer Paid Amount cannot exceed 1 billion or minimum 1 is required");
		}

		if (customerLoanApplication.getCustomerTotalLoanRequired() < 1
				|| customerLoanApplication.getCustomerTotalLoanRequired() > 1000000000.00) {
			throw new HandleCustomException(
					"Customer Total Laon Amount cannot exceed 1 billion or minimum 1 is required");
		}
		if (!customerLoanApplication.getLoanStatus().matches("Submit|Verified|Sanctioned|Disbursed")) {
			throw new HandleCustomException(
					"Loan status must be one of the following: Submit, Verified, Sanctioned, Disbursed");
		}

		/*******************************************************************************************************************/

		if (!customerLoanApplication.getCustomerAddress().getPermanentAddress().getAreaName()
				.matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Permanant Address : Areaname must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getCustomerAddress().getPermanentAddress().getCityName()
				.matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Permanant Address : City must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getCustomerAddress().getPermanentAddress().getDistrict()
				.matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Permanant Address : District must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getCustomerAddress().getPermanentAddress().getState().matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Permanant Address : State must contain only alphabetic characters and spaces");
		}
		if (customerLoanApplication.getCustomerAddress().getPermanentAddress().getPincode() < 100000
				|| customerLoanApplication.getCustomerAddress().getPermanentAddress().getPincode() > 999999) {
			throw new HandleCustomException(
					"Permanant Address : Pincode must be a 6-digit number and not less than 100000 or note greter than 999999");
		}
		if (customerLoanApplication.getCustomerAddress().getPermanentAddress().getHouseNumber() < 0) {
			throw new HandleCustomException("Permanant Address : House number must be greater than 0");
		}

		if (!customerLoanApplication.getCustomerAddress().getPermanentAddress().getStreetName()
				.matches("^[a-zA-Z0-9 ]+$")) {
			throw new HandleCustomException(
					"Permanant Address : Street name can only contain letters, numbers, and spaces");
		}

		/*******************************************************************************************************************/

		if (!customerLoanApplication.getCustomerAddress().getLocalAddress().getAreaName().matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Local Address : Areaname must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getCustomerAddress().getLocalAddress().getAreaName().matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Local Address : Areaname must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getCustomerAddress().getLocalAddress().getDistrict().matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Local Address : District must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getCustomerAddress().getLocalAddress().getState().matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException("Local Address : State must contain only alphabetic characters and spaces");
		}
		if (customerLoanApplication.getCustomerAddress().getLocalAddress().getPincode() < 100000
				|| customerLoanApplication.getCustomerAddress().getPermanentAddress().getPincode() > 999999) {
			throw new HandleCustomException(
					"Local Address : Pincode must be a 6-digit number and not less than 100000 or note greter than 999999");
		}
		if (customerLoanApplication.getCustomerAddress().getLocalAddress().getHouseNumber() < 0) {
			throw new HandleCustomException("Local Address : House number must be greater than 0");
		}

		if (!customerLoanApplication.getCustomerAddress().getLocalAddress().getStreetName()
				.matches("^[a-zA-Z0-9 ]+$")) {
			throw new HandleCustomException(
					"Local Address : Street name can only contain letters, numbers, and spaces");
		}

		/*******************************************************************************************************************/

		if (!customerLoanApplication.getAccountDetails().getAccounType().matches("^(Savings|Current|Fixed Deposit)$")) {
			throw new HandleCustomException(
					"Account Details : Account Type must be 'Savings', 'Current', or 'Fixed Deposit'");
		}

		if (customerLoanApplication.getAccountDetails().getAccountBalance() < 0) {
			throw new HandleCustomException("Account Details : Account balance must be non-negative");
		}
		if (!customerLoanApplication.getAccountDetails().getAccountHolderName().matches("^[A-Z][a-zA-Z]*$")) {
			throw new HandleCustomException(
					"Account Details : AccountHolderName must start with a capital letter and contain only alphabets");
		}
		if (!customerLoanApplication.getAccountDetails().getAccountStatus().matches("^(Active|Inactive|Closed)$")) {
			throw new HandleCustomException(
					"Account Details : Account status must be 'Active', 'Inactive', or 'Closed'");
		}
		if (customerLoanApplication.getAccountDetails().getAccountNumber() < 1000000000L
				|| customerLoanApplication.getAccountDetails().getAccountNumber() > 9999999999L) {
			throw new HandleCustomException(
					"Account Details : Account number must be at least 10 digits or Account number cannot exceed 10 digits");
		}
		/*******************************************************************************************************************/
		if (customerLoanApplication.getFamilyDependentInfo().getNoOfFamilyMember() < 0)

		{
			throw new HandleCustomException("Family Details : Number of family members is required");
		}
		if (customerLoanApplication.getFamilyDependentInfo().getNoOfChild() < 0)

		{
			throw new HandleCustomException("Family Details : Number of Child is required");
		}

		if (!customerLoanApplication.getFamilyDependentInfo().getMaritalStatus().matches("^(Single|Married|Divorced)$"))

		{
			throw new HandleCustomException(
					"Family Details : Marital status must be 'Single', 'Married', or 'Divorced'");
		}
		if (!customerLoanApplication.getFamilyDependentInfo().getDependentMember().matches("^[a-zA-Z\\s]*$"))

		{
			throw new HandleCustomException(
					"Family Details : dependentMember must contain only alphabetic characters and spaces'");
		}

		if (customerLoanApplication.getFamilyDependentInfo().getFamilyIncome() < 10000)

		{
			throw new HandleCustomException("Family Details : Family income must be greater than 10000");
		}

		/*******************************************************************************************************************/
		if (!customerLoanApplication.getGurantorDetails().getGuarantorName().matches("^[A-Z][a-zA-Z]*$")) {
			throw new HandleCustomException(
					"Guarantor Details : GuarantorName must start with a capital letter and contain only alphabets");
		}
		if (!customerLoanApplication.getGurantorDetails().getGuarantorDateOfBirth().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			throw new HandleCustomException(
					"Guarantor Details : GuarantorDateOfBirth must be in the format yyyy-MM-dd");
		}
		if (!customerLoanApplication.getGurantorDetails().getGuarantorRelationshipwithCustomer()
				.matches("^(Friend|Colleague|Family Member|Other)$")) {
			throw new HandleCustomException(
					"Guarantor Details : Guarantor relationship must be 'Friend', 'Colleague', 'Family Member', or 'Other'");
		}

		if (customerLoanApplication.getGurantorDetails().getGuarantorMobileNumber() < 1000000000
				|| customerLoanApplication.getGurantorDetails().getGuarantorMobileNumber() > 9999999999L) {
			throw new HandleCustomException("Guarantor Details : Mobile number must be at most 10 digits");
		}
		if (customerLoanApplication.getGurantorDetails().getGuarantorAdharCardNo() < 100000000000L
				|| customerLoanApplication.getGurantorDetails().getGuarantorAdharCardNo() > 999999999999L) {
			throw new HandleCustomException("Guarantor Details : Aadhaar card number must be a 12-digit number");
		}

		if (customerLoanApplication.getGurantorDetails().getGuarantorMortgageDetails() == null) {
			throw new HandleCustomException("Guarantor Details : uarantor mortgage details are required");
		}

		if (!customerLoanApplication.getGurantorDetails().getGuarantorJobDetails().matches("^[a-zA-Z\\s]*$")) {
			throw new HandleCustomException(
					"Guarantor Details :  guarantorJobDetails must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getGurantorDetails().getGuarantorLocalAddress().matches("^[a-zA-Z0-9 ]+$")) {
			throw new HandleCustomException(
					"Guarantor Details :  guarantorLocalAddress must contain only alphabetic characters and spaces");
		}
		if (!customerLoanApplication.getGurantorDetails().getGuarantorPermanentAddress().matches("^[a-zA-Z0-9 ]+$")) {
			throw new HandleCustomException(
					"Guarantor Details :  guarantorPermanentAddress must contain only alphabetic characters and spaces");
		}

		/*******************************************************************************************************************/
		if (customerLoanApplication != null) {
			customerLoanApplication.setCustomerName(details.getFirstName() + details.getLastName());
			customerLoanApplication.setCustomerAge(details.getAge());
			customerLoanApplication.setCustomerEmail(details.getEmail());
			customerLoanApplication.setCustomerMobileNumber(details.getMobileNo());
			customerLoanApplication.setCibilScoreData(details.getCibilScoreData());
		}
		if (customerLoanApplication.getAllPersonalDocument() == null) {
			customerLoanApplication.setAllPersonalDocument(new AllPersonalDocs());
		}

		try {
			customerLoanApplication.getAllPersonalDocument().setAddressProof(addressProof.getBytes());
			customerLoanApplication.getAllPersonalDocument().setAddharCard(addharCard.getBytes());
			customerLoanApplication.getAllPersonalDocument().setPanCard(panCard.getBytes());
			customerLoanApplication.getAllPersonalDocument().setIncomeTax(incomeTax.getBytes());
			customerLoanApplication.getAllPersonalDocument().setPhoto(photo.getBytes());
			customerLoanApplication.getAllPersonalDocument().setSignature(signature.getBytes());
			customerLoanApplication.getAllPersonalDocument().setBankCheque(bankCheque.getBytes());
			customerLoanApplication.getAllPersonalDocument().setSalarySlips(salarySlips.getBytes());
			System.out.println(customerLoanApplication);
			customerLoanApplicationRepository.save(customerLoanApplication);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return customerLoanApplication;

	}

	@Override
	public List<CustomerLoanApplication> getAllCustomerApplicationData() {

		return customerLoanApplicationRepository.findAll();
	}

	@Override
	public CustomerLoanApplication updateLoanStatus(int id, String loanStatus) {
		CustomerLoanApplication customerLoanApplication = customerLoanApplicationRepository.findByCustomerLoanID(id);
		customerLoanApplication.setLoanStatus(loanStatus);
		CustomerLoanApplication customerLoanApplication2 = customerLoanApplicationRepository
				.save(customerLoanApplication);
		return customerLoanApplication2;
	}

	@Override
	public List<CustomerLoanApplication> getAllLoansubmited() {
		String status = "Submit";
		return customerLoanApplicationRepository.findAllByLoanStatus(status);
	}

	@Override
	public List<CustomerLoanApplication> getAllVerifiedData() {
		String status = "Verified";
		return customerLoanApplicationRepository.findAllByLoanStatus(status);
	}

	

	@Override
	public CustomerLoanApplication updateLoanStatusofCustomerApplication(int id, String loanStatus) {
		CustomerLoanApplication customerLoanApplication = customerLoanApplicationRepository.findByCustomerLoanID(id);
		customerLoanApplication.setLoanStatus(loanStatus);
		CustomerLoanApplication customerLoanApplication2 = customerLoanApplicationRepository
				.save(customerLoanApplication);
		return customerLoanApplication2;

	}

	@Override
	public List<CustomerLoanApplication> getAllSanctioedData() {
		String status = "Sanctioned";
		return customerLoanApplicationRepository.findAllByLoanStatus(status);
	}

	@Override
	public CustomerLoanApplication updateLoandisBursement(int customerLoanId,
			CustomerLoanApplication customerLoanApplication) {
		Optional<CustomerLoanApplication> customerLoanapp = customerLoanApplicationRepository.findById(customerLoanId);
		if(customerLoanapp.isPresent()) {
			customerLoanapp.get().setLoandisbursement(customerLoanApplication.getLoandisbursement());
			System.out.println(customerLoanapp.get());
			return customerLoanApplicationRepository.save(customerLoanapp.get());
			
		}
		
		throw new HandleCustomException("CustomerLoanId is Invalid");

	}

	@Override
	public CustomerLoanApplication getCustomerLoanApplication(int customerLoanID) {
		Optional<CustomerLoanApplication> custLoanApp=customerLoanApplicationRepository.findById(customerLoanID);
		if(custLoanApp.isPresent()) {
			return custLoanApp.get();
		}
		throw new HandleCustomException("CustomerLoanId is Invalid");
	}

	@Override
	public AllPersonalDocs updateDocument(int customerid, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo, MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips) {
		CustomerLoanApplication application = customerLoanApplicationRepository.findByCustomerLoanID(customerid);
		 System.out.println("hello");
			try {
				application.getAllPersonalDocument().setAddressProof(addressProof.getBytes());
				application.getAllPersonalDocument().setAddharCard(addharCard.getBytes());
				application.getAllPersonalDocument().setPanCard(panCard.getBytes());
				application.getAllPersonalDocument().setIncomeTax(incomeTax.getBytes());
				application.getAllPersonalDocument().setPhoto(photo.getBytes());
				application.getAllPersonalDocument().setSignature(signature.getBytes());
				application.getAllPersonalDocument().setBankCheque(bankCheque.getBytes());
				application.getAllPersonalDocument().setSalarySlips(salarySlips.getBytes());
				application.getAllPersonalDocument().setAddharCard(addharCard.getBytes());
				application.getAllPersonalDocument().setPanCard(panCard.getBytes());
				application.getAllPersonalDocument().setIncomeTax(incomeTax.getBytes());
				application.getAllPersonalDocument().setPhoto(photo.getBytes());
				application.getAllPersonalDocument().setSignature(signature.getBytes());
				application.getAllPersonalDocument().setBankCheque(bankCheque.getBytes());
				application.getAllPersonalDocument().setSalarySlips(salarySlips.getBytes());
				CustomerLoanApplication application2 = customerLoanApplicationRepository.save(application);
				return application2.getAllPersonalDocument();
			} catch (IOException e) {
				e.printStackTrace();
			

		}

		return null;
	}

}