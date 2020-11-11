package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.BloodDrive;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BloodDriveRepository extends CrudRepository<BloodDrive, Integer> {

    @Query(value =
            "SELECT * FROM blood_drives where blood_drive_date >= CURRENT_DATE() AND blood_drive_time >= CURRENT_TIME()" +
                    " ORDER BY blood_drive_date ASC, blood_drive_time ASC",
            nativeQuery = true
    )
    List<BloodDrive> findUpcomingBloodDrives();

    @Query(value="DELETE FROM blood_drives WHERE blood_drive_date < CURRENT_DATE()", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteOutdatedBloodDrives();
}
