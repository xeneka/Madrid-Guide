package  es.elprincipe.madridguide.manager.db;

import static es.elprincipe.madridguide.manager.db.DBActivityContants.SQL_SCRIPT_CREATE_DESCRIPTION_TABLE;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.SQL_SCRIPT_CREATE_ACTIVITY_TABLE;
import static es.elprincipe.madridguide.manager.db.DBShopConstants.SQL_SCRIPT_CREATE_SHOP_TABLE;

public class DBConstants {
    public static final String DROP_DATABASE = "";



    public static final String DROP_DATABASE_SCRIPT = "";

    public static final String[] CREATE_DATABASE_SCRIPT = {
            SQL_SCRIPT_CREATE_SHOP_TABLE,
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE,
            SQL_SCRIPT_CREATE_DESCRIPTION_TABLE

    };
}
