package com.example.android.friendship;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chris and Joe and Prain on 1/31/2015.
 */
public class TaskAdapter extends BaseAdapter {
    public final ArrayList<Task> mItems = new ArrayList<Task>();
    private final Context mContext;
    private final String TAG ="Hackathon-Friendship";
    private Context context;
    public TaskAdapter(Context context){
        mContext = context;
        this.context = context;
    }

    public void add(Task item){
        mItems.add(item);
        notifyDataSetChanged();
    }
    public void update(Task item, int pos){
        mItems.get(pos).setTitle(item.getTitle());
        mItems.get(pos).setDate(item.getDate());
        notifyDataSetChanged();
    }

    public void delete(int pos){
        mItems.remove(pos);
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
    public Task getItem(int pos) {

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
        final int itemPos;
        itemPos = position;
        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.task, null);

        final TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
        titleView.setText(toDoItem.getTitle());



        final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
        statusView.setChecked(toDoItem.getStatus());

        statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                toDoItem.setStatus(isChecked ? true : false);


            }
        });
        final ImageButton button = (ImageButton) itemLayout.findViewById(R.id.btnEdit);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//
              log(""+itemPos);

                Intent intent = new Intent(context, EditTask.class);
                intent.putExtra("position", String.valueOf(itemPos));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // Start an Activity using that intent and the request code defined above
                ( v.getContext()).startActivity(intent);

                //Perform action on click
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
    public void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);
    }
}
