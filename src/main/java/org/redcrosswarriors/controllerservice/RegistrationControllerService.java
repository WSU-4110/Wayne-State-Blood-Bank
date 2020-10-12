package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.AccountDetails;
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
