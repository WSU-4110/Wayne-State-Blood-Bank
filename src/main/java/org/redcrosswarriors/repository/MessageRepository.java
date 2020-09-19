package org.redcrosswarriors.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.redcrosswarriors.model.Message;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{

    @Query(value="SELECT * FROM messages WHERE message LIKE %:searchKey%", nativeQuery=true)
    List<Message> searchMessages(@Param("searchKey") String searchKey);
    
}
