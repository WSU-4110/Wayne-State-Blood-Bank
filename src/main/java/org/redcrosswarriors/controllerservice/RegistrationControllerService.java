package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.emailservice.send_mail;
import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.input.RegisterationInput;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.RegistrationDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.redcrosswarriors.model.RegistrationDetails;
import javax.transaction.Transactional;

@Service
public class RegistrationControllerService {


    @Autowired // dependencies injection, automatically
    private RegistrationDetailsRepository registrationRepository;

    @Autowired
    private AccountDetailsService accountService;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Transactional
    public boolean registerAccount(RegisterationInput registerInput) {
        // validate input
        AccountDetails account = new AccountDetails();
        account.setRoles("UNVERIFIED");
        account.setEmail(registerInput.getEmail());
        account.setPassword(registerInput.getPassword());
        accountService.createAccount(account);

        // validation
        ////////////////BEGIN: send the verification email/////////////////////////
        VerificationToken token = new VerificationToken();
        String link = "http://localhost:8080/verify/"+token;

        send_mail verify;
        verify = new send_mail(registerInput.getEmail(), link);
        try {
            verify.send_verification();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //////////////END: send the verification email////////////////////////////

        registrationRepository.registerAccount(registerInput.getFirstName(),
                    registerInput.getLastName(),
                    registerInput.getBirthDay(),
                    registerInput.getBloodDonor(),
                    registerInput.getPhoneNumber(),
                    registerInput.getBloodType());


        return true;
    }
    //public boolean userValid

    public boolean userExists(RegisterationInput input)
    {
        boolean check = false;
        AccountDetails account = accountDetailsRepository.findByEmail(input.getEmail());
        if (account != null)
        {
            check = true;
        } else if (account == null) {
            check = false;
        }
        return check;


    }

}
