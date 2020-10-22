package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TestRequest {

    @Test
    void request_constructor_test(){

        Request req = new Request("Vlad Thirsty",
                "vlad@the_impaler.com",
                "248-555-1212",
                "I am so thirsty pleeze help. This is a unit test");

        assertEquals(req.getName(), "Vlad Thirsty");
        assertEquals(req.getEmailAddress(), "vlad@the_impaler.com");
        assertEquals(req.getPhoneNumber(), "248-555-1212");
        assertEquals(req.getMessage(), "I am so thirsty pleeze help. This is a unit test");
    }
}