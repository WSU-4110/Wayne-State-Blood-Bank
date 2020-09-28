package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.Role;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.error.ErrorException;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.redcrosswarriors.repository.RoleRepository;
import org.redcrosswarriors.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository repository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private RoleRepository roleRepository;


    public VerificationToken createVerificationToken(String email){
        UUID uuid = UUID.randomUUID();
        repository.createVerificationTokenByEmail(email, uuid.toString());
        Optional<VerificationToken> token = repository.findById(uuid.toString());
        return token.get();
    }

    @Transactional
    public ResponseEntity<Object> verifyUser(String token){
        ResponseEntity<Object> response;
        try{
            // check to see if the token exists
            Optional<VerificationToken> realToken = repository.findById(token);
            if(!realToken.isPresent()){
                throw new ErrorException("Token expired", HttpStatus.BAD_REQUEST);
            }

            int accountId = realToken.get().getAccountId();

            // remove the unverified role from the users account
            Role unverified = roleRepository.getRoleByName("UNVERIFIED");
            accountDetailsRepository.removeRole(unverified.getId(), accountId);

            // add the user role to the account
            Role userRole = roleRepository.getRoleByName("USER");
            accountDetailsRepository.addRole(userRole.getId(), accountId);

            // delete the token
            repository.deleteById(token);

            response = new ResponseEntity<>("user successfully verified please log in again to access your account", HttpStatus.OK);
        }
        catch (ErrorException e){
            response = new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
        catch (Exception e){
            e.printStackTrace();
            response = new ResponseEntity<>("An unknown error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
