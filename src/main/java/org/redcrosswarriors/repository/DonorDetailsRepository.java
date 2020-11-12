package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.DonorDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

@Repository
public interface DonorDetailsRepository extends CrudRepository<DonorDetails, String> {

    @Modifying
    @Query(value= "INSERT INTO userDetails(firstName, lastName, birthDay, bloodDonor, phoneNumber, bloodType) " +
            "VALUES(:firstName, :lastName, :birthDay, :bloodDonor, :phoneNumber, :bloodType)", nativeQuery = true)
    void registerAccount(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("birthDay") String birthDay,
            @Param("bloodDonor") String bloodDonor,
            @Param("phoneNumber") String phoneNumber,
            @Param("bloodType") String bloodType);

    @Modifying
    @Query(value = "INSERT INTO donorList VALUES( :id, :email, :birthDay, :bloodType, :phoneNumber)", nativeQuery = true)
    void addDonor(@Param("id") int id,
                  @Param("email") String email,
                  @Param("birthDay") String birthDay,
                  @Param("bloodType") String bloodType,
                  @Param("phoneNumber") String phoneNumber);


    @Modifying
    @Transactional
    @Query(value="DELETE FROM userDetails WHERE id = :id", nativeQuery = true)
    void removeAccountById(@Param("id") int id);

    @Modifying
    @Query(value="UPDATE userDetails SET bloodDonor = :bloodDonor WHERE id = :id", nativeQuery=true)
    void updateDonor(@Param("id") int id, @Param("bloodDonor") String bloodDonor);

}