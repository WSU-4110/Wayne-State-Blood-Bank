package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.DatabaseConnection;
import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.input.EditProfileInput;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
        DatabaseConnection connection = DatabaseConnection.getDatabaseConnection();

        try{
            if(input.getPassword() != null){
                accountDetailsService.updatePassword(email, input.getPassword());
            }

            EntityManager manager = connection.getManager();

            // start transaction
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            String updateSQL = getUpdateSQL(input);
            Query updateQuery = manager.createNativeQuery(updateSQL);
            if(updateSQL.contains("firstName")){
                updateQuery.setParameter("firstName", input.getFirstName());
            }
            if(updateSQL.contains("lastName")){
                updateQuery.setParameter("lastName", input.getLastName());
            }
            if(updateSQL.contains("phoneNumber")){
                updateQuery.setParameter("phoneNumber", input.getPhoneNumber());
            }
            if(updateSQL.contains("bloodDonorStatus")){
                updateQuery.setParameter("bloodDonorStatus", input.getBloodDonorStatus());
            }
            updateQuery.setParameter("email", email);
            updateQuery.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            responseObject.put("message", "An unknown error has occurred");
            return new ResponseEntity<Object>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        responseObject.put("message", "Successfully updated profile");
        return new ResponseEntity<Object>(responseObject, HttpStatus.OK);
    }

    private String getUpdateSQL(EditProfileInput input){
        String sqlQuery = "UPDATE user_details SET";
        if(input.getFirstName() != null){
            sqlQuery +=" first_name = :firstName,";
        }
        if(input.getLastName() != null){
            sqlQuery += " last_name = :lastName,";
        }
        if(input.getPhoneNumber() != null){
            sqlQuery += " phone_number = :phoneNumber,";
        }
        if(input.getBloodDonorStatus() !=  null){
            sqlQuery += " blood_donor_status = :bloodDonorStatus,";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1); // remove the last character of the string
        sqlQuery += " WHERE id = (SELECT id FROM accounts WHERE email = :email)";
        return sqlQuery;
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
