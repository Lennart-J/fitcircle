package de.jsauerwein.fitcircle;


import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class TrainingScheduleOverview extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ExerciseCursorAdapter exerciseCursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_trainingschedule_overview_fragment, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView exerciseList = (ListView) this.getActivity().findViewById(R.id.main_trainingschedule_exercises);
        exerciseCursorAdapter = new ExerciseCursorAdapter (this.getActivity(), null);

        exerciseList.setAdapter(exerciseCursorAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri =  TrainingScheduleContract.Exercises.CONTENT_URI;
        return new CursorLoader(this.getActivity(), baseUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        exerciseCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        exerciseCursorAdapter.swapCursor(null);
    }
}
