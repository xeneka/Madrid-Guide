package es.elprincipe.madridguide.activities;


import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;

public class ActivitiesTest extends AndroidTestCase {

    public void testCreateActivitiesWithNullValueReturnNotNullValue(){
        Activities sut = Activities.build(null);

        assertNotNull(sut);
        assertNotNull(sut.allActivities());
    }

    public void testCreateActivitiesWithData(){

        List<Activity> data = getActivities();

        Activities sut = Activities.build(data);
        assertNotNull(sut);
        assertEquals(sut.allActivities(), data);
        assertEquals(sut.allActivities().size(), data.size());
        assertEquals(sut.get(1).getName(),data.get(1).getName());



    }


    public List<Activity> getActivities(){

        List<Activity> activities= new ArrayList<>();
        activities.add(new Activity(1,"Actividad 1"));
        activities.add(new Activity(2,"Actividad 2"));

        return activities;


    }


}
