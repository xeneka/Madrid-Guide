package es.elprincipe.madridguide.Description;


import android.test.AndroidTestCase;

import es.elprincipe.madridguide.model.activity.Description;

public class DescriptionTest extends AndroidTestCase {

    public static final String DESCRIPCION_DE_PRUEBA = "descripcion de prueba";
    public static final String ACTIVITY_NAME = "museo";
    public static final String LANGUAGE = "es";

    public void testCanCreateDescription(){
        Description sut =new Description(1,LANGUAGE,DESCRIPCION_DE_PRUEBA,ACTIVITY_NAME);
        assertNotNull(sut);
    }
    public void testDataInaNewDescriptionIsOk(){
        Description sut =new Description(1,LANGUAGE,DESCRIPCION_DE_PRUEBA,ACTIVITY_NAME);
        assertEquals(sut.getId(),1);
        assertEquals(sut.getActivityName(),ACTIVITY_NAME);
    }


}
