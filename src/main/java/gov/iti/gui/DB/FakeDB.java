package gov.iti.gui.DB;

import gov.iti.gui.models.Person;

public class FakeDB {

    private static Person user ;

    public static void setUser(Person user) {
        FakeDB.user = user;
    }

    public static Person getUser() {
        return user;
    }

    
   
}
