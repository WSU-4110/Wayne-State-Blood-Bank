package org.redcrosswarriors.repository;
import org.redcrosswarriors.model.ViewFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewFeedbackRepository extends JpaRepository<ViewFeedback,Integer> {

}
