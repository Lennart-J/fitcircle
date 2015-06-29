package de.jsauerwein.fitcircle;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddExercise extends Fragment {

    private String name = "";
    private String type = "";
    private String difficulty = "";
    private boolean[] tools = new boolean[4];

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_trainingschedule_add_exercise, container, false);
        Button button = (Button) view.findViewById(R.id.exerciseAddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                name = ((EditText) view.findViewById(R.id.exerciseNameEdit)).getText().toString();
                type = ((EditText) view.findViewById(R.id.exerciseTypeEdit)).getText().toString();
                difficulty = ((EditText) view.findViewById(R.id.exerciseDifficultyEdit)).getText().toString();
                tools[0] = ((CheckBox) view.findViewById(R.id.checkBoxMat)).isChecked();
                tools[1] = ((CheckBox) view.findViewById(R.id.checkBoxExpander)).isChecked();
                tools[2] = ((CheckBox) view.findViewById(R.id.checkBoxBall)).isChecked();
                tools[3] = ((CheckBox) view.findViewById(R.id.checkBoxChair)).isChecked();

                if(!(name.isEmpty() || type.isEmpty() || difficulty.isEmpty())) {
                    addExercise(name, type, difficulty, tools);
                    backToOverview();
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Please fill the text fields...")
                        .setCancelable(false)
                        .setPositiveButton("OK", null);
                    AlertDialog alert = alertDialogBuilder.create();
                    alert.show();
                }
            }
        });
        return view;
    }

    public void backToOverview() {
        Intent overview = new Intent(AppContract.BROADCAST_ACTION_WORKOUT);

        overview.putExtra(InteractionModel.TAG_CURRENT_FRAGMENT, InteractionModel.WORKOUT_OVERVIEW);
        LocalBroadcastManager.getInstance(this.getActivity()).sendBroadcast(overview);
    }

    private void addExercise(String name, String type, String difficulty, boolean[] tools) {
        ContentValues values = new ContentValues();
        values.put(ExerciseTable.COLUMN_NAME, name);
        values.put(ExerciseTable.COLUMN_TYPE, type);
        values.put(ExerciseTable.COLUMN_DIFFICULTY, difficulty);
        Uri contentUri = TrainingScheduleContract.Exercises.CONTENT_URI;
        Uri targetUri = getActivity().getContentResolver().insert(contentUri, values);

        String exerciseId = targetUri.getLastPathSegment();

        for (int i = 0; i < tools.length; i++) {
            if (tools[i]) addExerciseToolRelationship(exerciseId, String.valueOf(i + 1));
        }
    }

    private void addExerciseToolRelationship(String exerciseId, String toolId) {
        ContentValues values = new ContentValues();
        values.put(Exercise2ToolsTable.COLUMN_EXERCISE_ID, exerciseId);
        values.put(Exercise2ToolsTable.COLUMN_TOOL_ID, toolId);
        Uri contentUri = Uri.parse(TrainingScheduleContract.Exercises.Tools.CONTENT_URI.toString().replace("#", exerciseId));
        Uri targetUri = getActivity().getContentResolver().insert(contentUri, values);
    }
}
