package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.emailservice.send_mail;
import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.RegistrationDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationControllerService {


    @Autowired // dependencies injection, automatically
    private RegistrationDetailsRepository registrationRepository;

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


        // create teh verification token
        VerificationToken token = tokenService.createVerificationToken(registerInput.getEmail());
        String link = "http://localhost:8080/verify/"+token.getToken();

        // send verificationEmail
        send_mail verify;
        verify = new send_mail(registerInput.getEmail(), link);
        try {
            verify.send_verification();
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
                    registerInput.getBloodDonor(),
                    registerInput.getPhoneNumber(),
                    registerInput.getBloodType());


        return true;
    }
    public boolean isNameValid(RegistrationInput registerInput)
    {
        boolean check = false;
        String firstName = registerInput.getFirstName();
        String lastName = registerInput.getLastName();
        // no white space
        // no numbers
        // Regex to check valid password.
        String regex = "^[a-zA-Z]+";

        String regex1 = "^\\d{10}";

        String regex2 = "^(A\\+)|(B\\+)|(A-)|(B-)|(O\\+)|(O-)|(AB\\+)|(AB-)";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (firstName == null || lastName == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher mFirst = p.matcher(firstName);
        Matcher mLast = p.matcher(lastName);

        // Return if the password
        // matched the ReGex
        if(mFirst.matches() && mLast.matches())
            check = true;

        return check;

    }

    public boolean isPhoneValid(RegistrationInput registerInput)
    {
        boolean check = false;
        String phoneNumber = registerInput.getPhoneNumber();

        // no white space
        // no numbers
        // Regex to check valid password.
        String regex = "^\\d{10}";



        String regex2 = "^(A\\+)|(B\\+)|(A-)|(B-)|(O\\+)|(O-)|(AB\\+)|(AB-)";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false


        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(phoneNumber);


        // Return if the password
        // matched the ReGex
        if(m.matches())
            check = true;

        return check;

    }

    public boolean isBloodTypeValid(RegistrationInput registerInput)
    {
        boolean check = false;
        String bloodType = registerInput.getBloodType();

        // no white space
        // no numbers
        // Regex to check valid password.
        String regex = "^(A\\+)|(B\\+)|(A-)|(B-)|(O\\+)|(O-)|(AB\\+)|(AB-)";


        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false


        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(bloodType);


        // Return if the password
        // matched the ReGex
        if(m.matches())
            check = true;

        return check;

    }




    public boolean isValidPassword(RegistrationInput registerInput)
    {
        String password = registerInput.getPassword();

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + ".{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }


    public boolean userExists(RegistrationInput input)
    {
        boolean check;
        AccountDetails account = accountDetailsRepository.findByEmail(input.getEmail());
        if (account != null)
        {
            check = true;
        }
        else
            check = false;

        return check;


    }

}
