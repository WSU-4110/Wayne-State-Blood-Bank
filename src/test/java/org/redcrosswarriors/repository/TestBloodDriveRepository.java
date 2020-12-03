package org.redcrosswarriors.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redcrosswarriors.model.BloodDrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestBloodDriveRepository {

    @Autowired
    private BloodDriveRepository repository;

    // add some test blood drives that are upcoming
    private void addRecentBloodDrives(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(calendar.getTime());
        // have the blood drive be the current day we run
        // the test so that the the upcomming blood drives query
        // always works
        BloodDrive testBloodDrive = new BloodDrive(
                "Wayne State Blood Drive",
                "Detroit MI",
                "23:59:59",
                today,
                "Blood drive today",
                "www.wayne.edu"
        );
        System.out.println("Today is: " + today);
        repository.save(testBloodDrive);
    }

    @Test
    public void testFindMostRecentBloodDrives(){
        addRecentBloodDrives();
        List<BloodDrive> bloodDrives = repository.findUpcomingBloodDrives();
        assertEquals(bloodDrives.size(), 1);
    }

    @Test
    @Sql("/test_delete_outdated_blood_drives.sql")
    public void testDeleteOutdatedBloodDrives(){
        repository.deleteOutdatedBloodDrives();
        List<BloodDrive> bloodDrives = StreamSupport.stream(
                repository.findAll().spliterator(),
                false
        ).collect(Collectors.toList());
        assertEquals(0, bloodDrives.size());
    }

}
