package es.elprincipe.madridguide.manager.db;


public class DBActivityContants {

    // Table Name
    public static final String TABLE_ACTIVITY ="ACTIVITY";

    // Table Field

    public static String KEY_ACTIVITY_ID ="_id";
    public static String KEY_ACTIVITY_NAME = "name";
    public static String KEY_ACTIVITY_IMAGE_URL = "imageUrl";
    public static String KEY_ACTIVITY_LOGO_IMAGE_URL = "logoImgUrl";
    public static String KEY_ACTIVITY_URL = "url";
    public static String KEY_ACTIVITY_TELEPHONE = "telephone";
    public static String KEY_ACTIVITY_EMAIL = "email";
    public static String KEY_ACTIVITY_ADDRESS = "address";
    public static String KEY_ACTIVITY_LATITUDE = "latitude";
    public static String KEY_ACTIVITY_LONGITUDE = "longitude";
    public static String KEY_ACTIVITY_BOOKING_OPERATION = "booking_operation";
    public static String KEY_ACTIVITY_BOOKING_DATA = "booking_data";


    public static final String[] ALLCOLUMNS = {
            KEY_ACTIVITY_ID,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_TELEPHONE,
            KEY_ACTIVITY_EMAIL,
            KEY_ACTIVITY_BOOKING_OPERATION,
            KEY_ACTIVITY_BOOKING_DATA
    };

    public static final String SQL_SCRIPT_CREATE_DESCRIPTION_TABLE =
            "create table "
                    + TABLE_ACTIVITY+ "( " + KEY_ACTIVITY_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_TELEPHONE + " text,"
                    + KEY_ACTIVITY_EMAIL + " text,"
                    + KEY_ACTIVITY_BOOKING_DATA + " text,"
                    + KEY_ACTIVITY_BOOKING_OPERATION + " text,"
                    + KEY_ACTIVITY_LATITUDE + " real,"
                    + KEY_ACTIVITY_LONGITUDE + " real "
                    + ");";



}

