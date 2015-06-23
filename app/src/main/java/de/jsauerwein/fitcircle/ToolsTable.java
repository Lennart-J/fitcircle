package de.jsauerwein.fitcircle;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by LennartJ on 23.06.2015.
 */
public class ToolsTable {
    public static final String TABLE_TOOLS = "tools";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "
                        + TABLE_TOOLS + "("
                        + COLUMN_ID + " integer primary key autoincrement, "
                        + COLUMN_NAME + " text not null);"
        );

        insert(sqLiteDatabase, "mat");
        insert(sqLiteDatabase, "expander");
        insert(sqLiteDatabase, "ball");
        insert(sqLiteDatabase, "chair");
    }

    public static void onUpgrade(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TOOLS);
        onCreate(sqLiteDatabase);
    }

    public static void insert(SQLiteDatabase sqLiteDatabase, String name) {
        sqLiteDatabase.execSQL("INSERT INTO "
                + TABLE_TOOLS + "("
                + COLUMN_NAME +  ") "
                + "VALUES('" + name + "');");
    }
}
