package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestEventRepository {

    @Autowired
    private EventRepository repository;

    @Test
    @Sql("/test_get_upcoming_events.sql")
    public void testGetUpcomingEvents(){
        List<Event> events = repository.getUpcomingEvents();
        assertEquals(1, events.size());
    }

    @Test
    @Sql("/test_delete_outdated_events.sql")
    public void testDeleteOutdatedEvents(){
        repository.deleteOutdatedEvents();
        List<Event> events = StreamSupport.stream(
                repository.findAll().spliterator(), false
        ).collect(Collectors.toList());
        assertEquals(0, events.size());
    }

}
