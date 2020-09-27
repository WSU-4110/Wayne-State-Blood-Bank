package org.redcrosswarriors.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.redcrosswarriors.model.AccountDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetails, String> {

    @Query(value="SELECT email, password, roles FROM account_details WHERE email = :email", nativeQuery=true)
    AccountDetails findByEmail(@Param("email") String email);

}
