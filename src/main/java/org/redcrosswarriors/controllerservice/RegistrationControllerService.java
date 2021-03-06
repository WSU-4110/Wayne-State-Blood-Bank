package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.emailservice.SendMail;
import org.redcrosswarriors.emailservice.SendVerificationEmail;
import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationControllerService {


    @Autowired // dependencies injection, automatically
    private UserDetailsRepository registrationRepository;

    @Autowired
    private AccountDetailsService accountService;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private VerificationTokenService tokenService;

    @Transactional
    public boolean registerAccount(RegistrationInput registerInput) {
        // validate input
        AccountDetails account = new AccountDetails();
        account.setRoles("UNVERIFIED");
        account.setEmail(registerInput.getEmail());
        account.setPassword(registerInput.getPassword());
        accountService.createAccount(account);


        // create the verification token and append to link
        VerificationToken token = tokenService.createVerificationToken(registerInput.getEmail());
        String link = "http://localhost:8080/verify/"+token.getToken();

        // send verificationEmail
        try {
            SendMail verify;
            verify = new SendVerificationEmail(registerInput.getEmail(), link);
            verify.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String firstName = registerInput.getFirstName();
        String lastName = registerInput.getLastName();
        firstName.replace(" ", "");
        lastName.replace(" ", "");
        registrationRepository.registerAccount(
                    accountDetailsRepository.findIdByEmail(registerInput.getEmail()),
                    firstName,
                    lastName,
                    registerInput.getBirthDay(),
                    registerInput.getBloodDonorStatus(),
                    registerInput.getPhoneNumber(),
                    registerInput.getBloodType());


        return true;
    }



    public boolean userExists(RegistrationInput input) {
        boolean check;
        AccountDetails account = accountDetailsRepository.findByEmail(input.getEmail());
        if (account != null) {
            check = true;
        }
        else
            check = false;

        return check;
    }

}
