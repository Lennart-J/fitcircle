package de.jsauerwein.fitcircle;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by LennartJ on 22.06.2015.
 */
public class ExerciseTable {

    public static final String TABLE_EXERCISES = "exercises";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DIFFICULTY = "difficulty";

    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "
                + TABLE_EXERCISES + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_TYPE + " text not null, "
                + COLUMN_DIFFICULTY + " text not null" +");"
        );
        insert(sqLiteDatabase, "Exercise 42", "1", "4");
        insert(sqLiteDatabase, "Exercise 32", "2", "6");
        insert(sqLiteDatabase, "Exercise 22", "3", "8");
        insert(sqLiteDatabase, "Exercise 12", "4", "2");
        insert(sqLiteDatabase, "Exercise 33", "5", "3");
        insert(sqLiteDatabase, "Exercise 44", "6", "6");
        insert(sqLiteDatabase, "Exercise 55", "7", "1");
        insert(sqLiteDatabase, "Exercise 1", "8", "2");
    }

    public static void onUpgrade(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(sqLiteDatabase);
    }

    public static void insert(SQLiteDatabase sqLiteDatabase, String name, String type, String difficulty) {
        sqLiteDatabase.execSQL ( "INSERT INTO "
                + TABLE_EXERCISES + "("
                + COLUMN_NAME + ", " + COLUMN_TYPE + ", " + COLUMN_DIFFICULTY + ") "
                + "VALUES('" + name + "', '" + type + "', '" + difficulty + "');");
    }
}
