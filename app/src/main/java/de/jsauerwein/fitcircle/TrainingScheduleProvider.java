package de.jsauerwein.fitcircle;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.List;

public class TrainingScheduleProvider extends ContentProvider {

    private SQLiteHelper dbHelper;

    public TrainingScheduleProvider() {
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String[] columnNames;
        Cursor cursor;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch(TrainingScheduleContract.URI_MATCHER.match(uri)) {
            case TrainingScheduleContract.EXERCISE_LIST:
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                queryBuilder.setTables(ExerciseTable.TABLE_EXERCISES);

                cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case TrainingScheduleContract.EXERCISE_TOOL_LIST:
                List<String> pathSegments = uri.getPathSegments();
                int exercise = Integer.valueOf(pathSegments.get(pathSegments.size() - 2));

                SQLiteQueryBuilder queryBuilderTools = new SQLiteQueryBuilder();
                queryBuilderTools.setTables(Exercise2ToolsTable.TABLE_EXERCISE_TO_TOOLS);
                queryBuilderTools.appendWhere(Exercise2ToolsTable.COLUMN_EXERCISE_ID + "=" + exercise);

                cursor = queryBuilderTools.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);

                return cursor;
            default:
                throw new UnsupportedOperationException("Resource not support.");
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch(TrainingScheduleContract.URI_MATCHER.match(uri)) {
            case TrainingScheduleContract.EXERCISE_LIST:
                return TrainingScheduleContract.Exercises.CONTENT_TYPE;
            case TrainingScheduleContract.EXERCISE_TOOL_LIST:
                return TrainingScheduleContract.Exercises.Tools.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Resource not support.");
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new SQLiteHelper(this.getContext());
        return false;
    }
}
