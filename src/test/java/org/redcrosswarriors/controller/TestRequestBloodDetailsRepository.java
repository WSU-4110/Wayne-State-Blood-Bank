package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.Profile;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestRequestBloodDetailsRepository {
    RequestBloodDetailsRepository repository;

    @Test
    @Sql("/test_request_blood_details.sql")
    public void testFindMatches(){
        List<String> matches = repository.findMatches("A+");
        assertEquals(2, matches.size());
    }
}