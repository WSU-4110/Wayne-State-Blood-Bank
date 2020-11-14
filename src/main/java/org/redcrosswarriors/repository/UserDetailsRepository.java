package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDetailsRepository extends CrudRepository<Profile, String> {

    @Modifying
    @Query(value= "INSERT INTO user_details(id, first_name, last_name, birth_day, blood_donor_status, phone_number, blood_type) " +
            "VALUES(:id, :firstName, :lastName, :birthDay, :bloodDonor, :phoneNumber, :bloodType)", nativeQuery = true)
    void registerAccount(
                         @Param("id") int id,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("birthDay") String birthDay,
                         @Param("bloodDonor") String bloodDonor,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("bloodType") String bloodType);


    @Query(value = "SELECT * FROM vw_user_profile WHERE email = :email", nativeQuery = true)
    Profile getProfileByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_details SET first_name = :firstName, last_name = :lastName, phone_number = :phoneNumber," +
            "blood_donor_status = :bloodDonorStatus WHERE id = (SELECT id FROM accounts WHERE email = :email) ",
            nativeQuery = true)
    void updateProfileByEmail(
            @Param("email") String email,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phoneNumber") String phoneNumber,
            @Param("bloodDonorStatus") String bloodDonorStatus);


    ////////////////////////////////////////////////////////////
    @Query(value = "SELECT * FROM vw_user_profile", nativeQuery = true)
    List<Profile> getAllProfiles();
    ////////////////////////////////////////////////////////////

}

