package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.RequestedTimeDetails;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RequestBloodDetailsRepository extends CrudRepository<RequestBloodInput, String>
{
    @Query(value="SELECT A.email\n" +
            "FROM vw_user_profile\n" +
            "WHERE blood_donor_status ='Y' AND blood_type = :bloodType", nativeQuery=true)
    List<String> findMatches(@Param("bloodType") String bloodType);

    @Query(value = "SELECT * FROM requester_time WHERE email = :email", nativeQuery = true)
    RequestedTimeDetails getRequestedTimeByEmail(@Param("email") String email);

    @Modifying

    @Query(value= "INSERT INTO requester_details(first_name, last_name, email, phone_number, blood_type, hospital_name, street_name, city_name, state_name, zip_code, message) " +
            "VALUES(:firstName, :lastName, :email, :phoneNumber, :bloodType, :hospitalName, :street, :city, :state, :zipCode, :message)", nativeQuery = true)
    void newRequester(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber,
            @Param("bloodType") String bloodType,
            @Param("hospitalName") String hospitalName,
            @Param("street") String street,
            @Param("city") String city,
            @Param("zipCode") String zipCode,
            @Param("message") String message);

    @Modifying
    @Query(value= "INSERT INTO requester_time(email, time_requested) " +
            "VALUES(:email, :time_requested)", nativeQuery = true)
    void requesterTimeUpdate(

            @Param("email") String email,
            @Param("time_requested") String time_requested);



    @Modifying
    @Query(value="UPDATE requester_time SET time_requested = :timeRequested WHERE email = :email", nativeQuery=true)
    void updateTime(@Param("timeRequested") String timeRequested, @Param("email") String email);
}
