package de.jsauerwein.fitcircle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LennartJ on 22.06.2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_EXERCISES = "exercises";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DIFFICULTY = "_id";

    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table "
                + TABLE_EXERCISES + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_TYPE + " text not null, "
                + COLUMN_DIFFICULTY + " text not null, " +");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(sqLiteDatabase);
    }
}
