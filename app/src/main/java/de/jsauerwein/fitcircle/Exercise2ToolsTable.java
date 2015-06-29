package de.jsauerwein.fitcircle;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by LennartJ on 23.06.2015.
 */
public class Exercise2ToolsTable {
    public static final String TABLE_EXERCISE_TO_TOOLS = "exercise_to_tools";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXERCISE_ID = "exercise_id";
    public static final String COLUMN_TOOL_ID = "tool_id";

    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "
                        + TABLE_EXERCISE_TO_TOOLS + "("
                        + COLUMN_ID + " integer primary key autoincrement, "
                        + COLUMN_EXERCISE_ID + " integer not null, "
                        + COLUMN_TOOL_ID + " integer not null, "
                        + "foreign key( " + COLUMN_EXERCISE_ID + ") references " + ExerciseTable.TABLE_EXERCISES + "(" + ExerciseTable.COLUMN_ID + "), "
                        + "foreign key( " + COLUMN_TOOL_ID + ") references " + ToolsTable.TABLE_TOOLS + "(" + ToolsTable.COLUMN_ID + ")"
                        + ");"
        );
        insert(sqLiteDatabase, "1", "1");
        insert(sqLiteDatabase, "1", "3");

        insert(sqLiteDatabase, "2", "1");
        insert(sqLiteDatabase, "2", "2");
        insert(sqLiteDatabase, "2", "4");

        insert(sqLiteDatabase, "3", "1");
        insert(sqLiteDatabase, "3", "2");
        insert(sqLiteDatabase, "3", "3");
        insert(sqLiteDatabase, "3", "4");

        insert(sqLiteDatabase, "4", "2");
        insert(sqLiteDatabase, "4", "4");

        insert(sqLiteDatabase, "5", "3");

        insert(sqLiteDatabase, "6", "4");

        insert(sqLiteDatabase, "7", "1");
        insert(sqLiteDatabase, "7", "2");

        insert(sqLiteDatabase, "8", "2");
        Log.d("LENNART", "exercise2tools Table created");
    }

    public static void onUpgrade(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_TO_TOOLS);
        onCreate(sqLiteDatabase);
    }

    public static void insert(SQLiteDatabase sqLiteDatabase, String exerciseId, String toolId) {
        sqLiteDatabase.execSQL ( "INSERT INTO "
                + TABLE_EXERCISE_TO_TOOLS + "("
                + COLUMN_EXERCISE_ID + ", " + COLUMN_TOOL_ID + ") "
                + "VALUES('" + exerciseId + "', '" + toolId + "');");
    }

}
