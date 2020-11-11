package org.redcrosswarriors.repository;

import org.redcrosswarriors.model.BloodDrive;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BloodDriveRepository extends CrudRepository<BloodDrive, Integer> {

    @Query(value =
            "SELECT * FROM blood_drives where blood_drive_date >= NOW()" +
                    " ORDER BY blood_drive_date ASC, blood_drive_time ASC",
            nativeQuery = true
    )
    List<BloodDrive> findMostRecentBloodDrives();

    @Query(value="DELETE FROM blood_drives WHERE blood_drive_date < NOW()", nativeQuery = true)
    void deleteOutdatedBloodDrives();
}
