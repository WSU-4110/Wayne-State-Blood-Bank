package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query(value="SELECT * FROM events WHERE event_date >= CURRENT_DATE() ORDER BY event_date DESC", nativeQuery=true)
    List<Event> getUpcomingEvents();

    @Query(value="DELETE FROM events WHERE event_date < CURRENT_DATE()", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteOutdatedEvents();
}
