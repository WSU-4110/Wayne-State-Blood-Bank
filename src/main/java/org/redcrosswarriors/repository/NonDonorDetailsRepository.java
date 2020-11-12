package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.NonDonorDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

@Repository
public interface NonDonorDetailsRepository extends CrudRepository<NonDonorDetails, String> {

    @Modifying
    @Query(value= "INSERT INTO nonDonorUserDetails(firstName, lastName, birthDay, bloodDonor, phoneNumber) " +
            "VALUES(:firstName, :lastName, :birthDay, :bloodDonor, :phoneNumber)", nativeQuery = true)
    void registerAccount(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("birthDay") String birthDay,
            @Param("bloodDonor") String bloodDonor,
            @Param("phoneNumber") String phoneNumber);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM userDetails WHERE id = :id", nativeQuery = true)
    void removeAccountById(@Param("id") int id);

    @Modifying
    @Query(value="UPDATE userDetails SET bloodDonor = :bloodDonor WHERE id = :id", nativeQuery=true)
    void updateDonor(@Param("id") int id, @Param("bloodDonor") String bloodDonor);

}
