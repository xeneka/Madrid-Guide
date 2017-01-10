package es.elprincipe.madridguide.manager.db;


public class DBDescriptionConstants {

    // Table Name
    public static final String TABLE_DESCRIPTION ="DESCRIPTION";

    // Table Field

    public static String KEY_DESCRIPTION_ID ="_id";
    public static String KEY_DESCRIPTION_ID_ACTIVITY = "idactitivy";
    public static String KEY_DESCRIPTION_LANGUAGE = "language";
    public static String KEY_DESCRIPTION_DESCRIPTION = "description";

    public static final String[] ALLCOLUMNS = {
            KEY_DESCRIPTION_ID,
            KEY_DESCRIPTION_ID_ACTIVITY,
            KEY_DESCRIPTION_LANGUAGE,
            KEY_DESCRIPTION_DESCRIPTION
    };

    public static final String SQL_SCRIPT_CREATE_ACTIVITY_TABLE =
            "create table "
                    + TABLE_DESCRIPTION+ "( " + KEY_DESCRIPTION_ID + " integer primary key autoincrement, "
                    + KEY_DESCRIPTION_ID_ACTIVITY + " integer not null,"
                    + KEY_DESCRIPTION_LANGUAGE + " text, "
                    + KEY_DESCRIPTION_DESCRIPTION + " text "

                    + ");";
}
