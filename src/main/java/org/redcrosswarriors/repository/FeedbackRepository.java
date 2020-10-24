package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {

}
