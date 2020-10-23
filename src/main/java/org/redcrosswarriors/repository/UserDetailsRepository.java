package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserDetailsRepository extends CrudRepository<Profile, String> {

    @Modifying
    @Query(value= "INSERT INTO userDetails(id, firstName, lastName, birthDay, bloodDonor, phoneNumber, bloodType) " +
            "VALUES(:id, :firstName, :lastName, :birthDay, :bloodDonor, :phoneNumber, :bloodType)", nativeQuery = true)
    void registerAccount(
                         @Param("id") int id,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("birthDay") String birthDay,
                         @Param("bloodDonor") String bloodDonor,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("bloodType") String bloodType);

}
