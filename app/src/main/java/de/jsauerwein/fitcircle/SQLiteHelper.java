package de.jsauerwein.fitcircle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LennartJ on 22.06.2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exercises.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("LENNART", "sqlhelper onCreate");
        ExerciseTable.onCreate(sqLiteDatabase);
        ToolsTable.onCreate(sqLiteDatabase);
        Exercise2ToolsTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        ExerciseTable.onUpgrade(sqLiteDatabase);
        ToolsTable.onUpgrade(sqLiteDatabase);
        Exercise2ToolsTable.onUpgrade(sqLiteDatabase);
    }
}
