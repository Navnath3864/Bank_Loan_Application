package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import com.app.exceptions.HandleCustomException;
import com.app.model.CustomerLoanApplication;
import com.app.repository.CustomerLoanApplicationRepository;
import com.app.service.EmailService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	CustomerLoanApplicationRepository applicationRepository;

	@Autowired
	JavaMailSender sender;
	
     @Value("${spring.mail.username}") 
     private String fromEmail;


	@Override
	public CustomerLoanApplication sendSanctionLetterMailToCustomer(int customerLoan_ID) {

		MimeMessage mimemessage = sender.createMimeMessage();

		CustomerLoanApplication customerLoanApplication = applicationRepository.findByCustomerLoanID(customerLoan_ID);
		byte[] sanctionLetter = customerLoanApplication.getSanctionLetter().getSanctionLetter();

		MimeMessageHelper mimemessageHelper;
		try {
			mimemessageHelper = new MimeMessageHelper(mimemessage, true);
			mimemessageHelper.setFrom(fromEmail);

			//mimemessageHelper.setTo(customerLoanApplication.getCustomerEmail());
			mimemessageHelper.setTo("juheeupadhye13@gmail.com");

			mimemessageHelper.setTo(customerLoanApplication.getCustomerEmail());

			mimemessageHelper.setSubject("Happy Finance Ltd. Sanction Letter");
			String text = "Dear " + customerLoanApplication.getCustomerName() + ",\n" + "\n"
					+ "This letter is to inform you that we have reviewed your request for a credit loan . We are pleased to offer you a credit loan of "
					+ customerLoanApplication.getSanctionLetter().getLoanAmtSanctioned() + " for "
					+ customerLoanApplication.getSanctionLetter().getLoanTenureInMonth() + ".\n" + "\n"
					+ "We are confident that you will manage your credit loan responsibly, and we look forward to your continued business.\n"
					+ "\n"
					+ "Should you have any questions about your credit loan, please do not hesitate to contact us.\n"
					+ "\n" + "Thank you for your interest in our services.";

			mimemessageHelper.setText(text);

			mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter));
			sender.send(mimemessage);

		} catch (MessagingException e) {
			System.out.println("Email Failed to Send!!!!!!");
			e.printStackTrace();
		}

		return null;
	}

}
