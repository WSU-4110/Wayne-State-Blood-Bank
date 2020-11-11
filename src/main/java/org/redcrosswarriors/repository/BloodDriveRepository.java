package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.BloodDrive;
import org.springframework.data.repository.CrudRepository;
// uses the default spring boot implementation of CRUDRepository
public interface BloodDriveRepository extends CrudRepository<BloodDrive, Integer> {

}
