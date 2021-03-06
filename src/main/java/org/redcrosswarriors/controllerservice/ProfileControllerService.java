package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.input.EditProfileInput;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.HashMap;

@Service
public class ProfileControllerService {

    @Autowired
    private UserDetailsRepository repository;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Transactional
    public ResponseEntity<Object> deleteProfile(String email){
        Map<String, Object> responseObject = new HashMap<String, Object>();
        try{
            accountDetailsService.deleteAccount(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            responseObject.put("message", "An unknown error has occurred");
            return new ResponseEntity<Object>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseObject.put("message", "Successfully deleted profile");
        return new ResponseEntity<Object>(responseObject, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> updateProfile(String email, EditProfileInput input){
        Map<String, Object> responseObject = new HashMap<String, Object>();

        try{
            if(input.getPassword() != null){
                accountDetailsService.updatePassword(email, input.getPassword());
            }

            repository.updateProfileByEmail(
                    email,
                    input.getFirstName(),
                    input.getLastName(),
                    input.getPhoneNumber(),
                    input.getBloodDonorStatus());
        }
        catch (Exception e) {
            e.printStackTrace();
            responseObject.put("message", "An unknown error has occurred");
            return new ResponseEntity<Object>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseObject.put("message", "Successfully updated profile");
        return new ResponseEntity<Object>(responseObject, HttpStatus.OK);
    }

    public ResponseEntity<Object> getProfile(String email){
        Profile profile;
        try{
            profile = repository.getProfileByEmail(email);
        }
        catch (Exception e){
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<String,String>();
            errorResponse.put("message", "An unknown error has occurred");
            return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Object>(profile, HttpStatus.OK);
    }
}
