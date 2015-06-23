package de.jsauerwein.fitcircle;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LennartJ on 18.06.2015.
 */
public class ExerciseCursorAdapter  extends CursorAdapter{

    private final int[] exerciseIcons= new int[] {
            R.drawable.pose1,
            R.drawable.pose2,
            R.drawable.pose3,
            R.drawable.pose4,
            R.drawable.pose5,
            R.drawable.pose6,
            R.drawable.pose7,
            R.drawable.pose8,
            R.drawable.pose9,
            R.drawable.pose10,
            R.drawable.pose11,
            R.drawable.pose12,
            R.drawable.pose13,
            R.drawable.pose14,
            R.drawable.pose15,
            R.drawable.pose16,
            R.drawable.pose17,
            R.drawable.pose18,
            R.drawable.pose19,
            R.drawable.pose20,
            R.drawable.pose21,
            R.drawable.pose22,
            R.drawable.pose23,
            R.drawable.pose24,
            R.drawable.pose25,
            R.drawable.pose26,
            R.drawable.pose27,
            R.drawable.pose28,
            R.drawable.pose29,
            R.drawable.pose30,
            R.drawable.pose31,
            R.drawable.pose32,
            R.drawable.pose33,
            R.drawable.pose34,
            R.drawable.pose35,
            R.drawable.pose36,
            R.drawable.pose37,
            R.drawable.pose38,
            R.drawable.pose39,
            R.drawable.pose40,
            R.drawable.pose41,
            R.drawable.pose42,
            R.drawable.pose43,
            R.drawable.pose44,
            R.drawable.pose45
    };

    private final int[] difficultyLevelTexts= new int[] {
            R.string.difficulty_easy,
            R.string.difficulty_medium,
            R.string.difficulty_hard
    };

    public ExerciseCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_trainingschedule_overview_exercise, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder();

        viewHolder.poseView = (ImageView) view.findViewById(R.id.main_trainingschedule_workout_type);
        viewHolder.nameView = (TextView) view.findViewById(R.id.main_trainingschedule_workout_name);
        viewHolder.difficultyView = (TextView) view.findViewById(R.id.main_trainingschedule_difficulty);
        viewHolder.tool1 = (ImageView) view.findViewById((R.id.main_trainingschedule_tool_mat));
        viewHolder.tool2 = (ImageView) view.findViewById((R.id.main_trainingschedule_tool_expander));
        viewHolder.tool3 = (ImageView) view.findViewById((R.id.main_trainingschedule_tool_ball));
        viewHolder.tool4 = (ImageView) view.findViewById((R.id.main_trainingschedule_tool_chair));

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        if (cursor != null && cursor.getColumnCount() >= 4) {
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String difficulty = cursor.getString(cursor.getColumnIndex("difficulty"));

            viewHolder.poseView.setImageResource(this.exerciseIcons[type - 1]);
            viewHolder.nameView.setText(name);
            viewHolder.difficultyView.setText(difficulty);
            viewHolder.tool1.setVisibility(View.GONE);
            viewHolder.tool2.setVisibility(View.GONE);
            viewHolder.tool3.setVisibility(View.GONE);
            viewHolder.tool4.setVisibility(View.GONE);

            ContentResolver contentResolver = context.getContentResolver();

            Uri toolsUri = Uri.parse(
                    new StringBuilder()
                            .append("content://de.jsauerwein.fitcircle.schedule/exercises/")
                            .append(cursor.getString(cursor.getColumnIndex("_id")))
                            .append("/tools").toString()
            );

            Cursor toolsCursor = contentResolver.query(toolsUri, null, null, null, null);

            while(toolsCursor.moveToNext()) {
                switch (toolsCursor.getInt(toolsCursor.getColumnIndex("tool_id"))) {
                    case 1:
                        viewHolder.tool1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        viewHolder.tool2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        viewHolder.tool3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        viewHolder.tool4.setVisibility(View.VISIBLE);
                        break;
                }
            }
            toolsCursor.close();
        }
    }

    private static class ViewHolder {
        private ImageView poseView;
        public TextView nameView;
        public TextView difficultyView;
        public ImageView tool1;
        public ImageView tool2;
        public ImageView tool3;
        public ImageView tool4;
    }
}
