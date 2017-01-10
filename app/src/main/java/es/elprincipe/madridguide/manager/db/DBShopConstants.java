package es.elprincipe.madridguide.manager.db;


public class DBShopConstants {

    public static final String TABLE_SHOP = "SHOP";



    // Table field constants
    public static final String KEY_SHOP_ID = "_id";
    public static final String KEY_SHOP_NAME = "NAME";
    public static final String KEY_SHOP_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";

    // Table field constants

    public static final String KEY_SHOP_LATITUDE = "latitude";
    public static final String KEY_SHOP_LONGITUDE = "longitude";
    public static final String KEY_SHOP_ADDRESS = "ADDRESS";

    public static final String KEY_SHOP_URL = "URL";
    public static final String KEY_SHOP_DESCRIPTION = "DESCRIPTION";

    public static final String[] ALLCOLUMNS = {
            KEY_SHOP_ID,
            KEY_SHOP_NAME,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_URL

    };


    public static final String SQL_SCRIPT_CREATE_SHOP_TABLE =
            "create table "
                    + TABLE_SHOP+ "( " + KEY_SHOP_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_DESCRIPTION + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real "


                    + ");";

}
