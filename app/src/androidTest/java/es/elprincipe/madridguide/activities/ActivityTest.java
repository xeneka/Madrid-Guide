package es.elprincipe.madridguide.activities;


import android.test.AndroidTestCase;

import java.util.LinkedList;

import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.model.activity.Categories;
import es.elprincipe.madridguide.model.activity.Routes;

public class ActivityTest extends AndroidTestCase {

    public static final String ACTIVITY_NAME = "Actividad de Prueba";
    public static final String CATEGORIA_1 = "Categoria 1";
    public static final String CATEGORIA_2 = "Categoria 1";
    public static final String RUTA_1 = "Ruta 1";
    public static final String RUTA_2 = "RUTA_2";

    public void testCanCreateShop(){
        Activity sut =new Activity(1,ACTIVITY_NAME);
        assertNotNull(sut);
    }

    public void testDataInaNewActivityIsOk(){
        Activity sut =new Activity(10,ACTIVITY_NAME);
        assertEquals(sut.getId(),10);
        assertEquals(sut.getName(),ACTIVITY_NAME);
    }

    public void testVerifiyListCategoriesProperty(){

        Categories categories1 = new Categories(1, CATEGORIA_1);
        Categories categories2 = new Categories(2, CATEGORIA_2);
        LinkedList<Categories> categories= new LinkedList<>();
        categories.add(categories1);
        categories.add(categories2);

        Activity sut = new Activity(12, ACTIVITY_NAME);
        sut.setCategories(categories);
        assertEquals(sut.getCountCartegories(),2);


    }

    public void testVerifyListRoutesProperty(){

        Routes route1 = new Routes(1, RUTA_1);
        Routes route2 = new Routes(2, RUTA_2);
        LinkedList<Routes> routes = new LinkedList<>();
        routes.add(route1);
        routes.add(route2);

        Activity sut = new Activity(12, ACTIVITY_NAME);
        sut.setRoutes(routes);

        assertNotNull("Registros insertados", sut.getCountRoutes());
        assertEquals(sut.getCountRoutes(),2);

    }


}
