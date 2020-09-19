package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.redcrosswarriors.model.Message;

@ExtendWith(SpringExtension.class)
@DataJpaTest // configures in memory database separate from our main database to use for testing
public class TestMessageRepository {

    @Autowired
    private MessageRepository repository;

    @Test
    @Sql("/test_messages.sql")
    public void testSearchMessages(){
        List<Message> messages = repository.searchMessages("Hello");
        assertEquals(1, messages.size());
    }
}
