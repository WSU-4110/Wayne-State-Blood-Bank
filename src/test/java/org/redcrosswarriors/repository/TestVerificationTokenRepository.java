package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestVerificationTokenRepository {
    @Autowired
    private VerificationTokenRepository repository;

    @Test
    @Sql("/test_account_details_no_roles.sql")
    public void testCreateVerificationTokenByEmail(){
        repository.createVerificationTokenByEmail("user@example.com", "testToken");
        assertNotNull(repository.findById("testToken"));
    }

}
