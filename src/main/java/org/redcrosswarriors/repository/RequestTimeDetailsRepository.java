package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.RequestInputDetails;
import org.redcrosswarriors.model.RequestedTimeDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestTimeDetailsRepository extends CrudRepository<RequestedTimeDetails, Integer>
{
    @Query(value = "SELECT * FROM requester_time WHERE email = :email", nativeQuery = true)
    RequestedTimeDetails getRequestedTimeByEmail(@Param("email") String email);

    @Modifying
    @Query(value= "INSERT INTO requester_time(email, time_requested) " +
            "VALUES(:email, :time_requested)", nativeQuery = true)
    void requesterTimeUpdate(
            @Param("email") String email,
            @Param("time_requested") String time_requested);

    @Modifying
    @Query(value="UPDATE requester_time SET time_requested = :timeRequested WHERE email = :email", nativeQuery=true)
    void updateTime(@Param("timeRequested") String timeRequested,
                    @Param("email") String email);
}
