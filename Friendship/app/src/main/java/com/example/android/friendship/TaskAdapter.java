package com.example.android.friendship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.android.friendship.Task.Status;
import java.util.ArrayList;

/**
 * Created by Chris and Joe and Prain on 1/31/2015.
 */
public class TaskAdapter extends BaseAdapter {
    private final ArrayList<Task> mItems = new ArrayList<Task>();
    private final Context mContext;
    private final String TAG ="Hackathon-Friendship";

    public TaskAdapter(Context context){
        mContext = context;
    }

    public void add(Task item){
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        mItems.clear();
        notifyDataSetChanged();
    }
   @Override
    public int getCount(){
       return mItems.size();
    }
    @Override
    public Object getItem(int pos) {

        return mItems.get(pos);

    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    @Override
    public long getItemId(int pos) {

        return pos;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        final Task toDoItem = (Task) getItem(position);

        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.task, null);

        final TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
        titleView.setText(toDoItem.getTitle());



        final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
        statusView.setChecked(Status.DONE.equals(toDoItem.getStatus()));

        statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                toDoItem.setStatus(isChecked ? Status.DONE : Status.NOTDONE);


            }
        });



        // TODO - Display Time and Date.
        // Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and time String

        final TextView dateLabel = (TextView) itemLayout.findViewById(R.id.DateLabel);
        //dateLabel.setText(Task.FORMAT.format(toDoItem.getDate()));
        dateLabel.setText(toDoItem.getDate());


        // Return the View you just created
        return itemLayout;
    }
}
