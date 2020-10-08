package org.redcrosswarriors.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.redcrosswarriors.model.input.registrationInput;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

@Repository
public interface RegistrationDetailsRepository extends CrudRepository<registrationInput, String> {

    @Modifying
    @Query(value= "INSERT INTO userDetails(id, firstName, lastName, birthDay, bloodDonor, phoneNumber, bloodType) " +
            "VALUES(:id, :firstName, :lastName, :birthDay, :bloodDonor, :phoneNumber, :bloodType)", nativeQuery = true)
    void registerAccount(@Param("id") int id,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("birthDay") String birthDay,
                         @Param("bloodDonor") char bloodDonor,
                         @Param("phoneNumber") int phoneNumber,
                         @Param("bloodType") char bloodType);

    @Modifying
    @Query(value = "INSERT INTO donorList VALUES( :id, :email, :birthDay, :bloodType, :phoneNumber)", nativeQuery = true)
    void addDonor(@Param("id") int id,
                  @Param("email") String email,
                  @Param("birthDay") String birthDay,
                  @Param("bloodType") char bloodType,
                  @Param("phoneNumber") int phoneNumber);


    @Modifying
    @Transactional
    @Query(value="DELETE FROM userDetails WHERE id = :id", nativeQuery = true)
    void removeAccountById(@Param("id") int id);

    @Modifying
    @Query(value="UPDATE userDetails SET bloodDonor = :bloodDonor WHERE id = :id", nativeQuery=true)
    void updateDonor(@Param("id") int id, @Param("bloodDonor") char bloodDonor);

}
