package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.VerificationToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, String> {

    @Modifying
    @Query(value = "INSERT INTO verification_tokens(account_id, token) VALUES((SELECT id FROM accounts WHERE email = :email), :token)", nativeQuery = true)
    void createVerificationTokenByEmail(@Param("email") String email, @Param("token") String token);

}
