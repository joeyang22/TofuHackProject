package com.example.android.friendship;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    private ListView listView;
    private Button btnAddNew;
    private static final int ADD_TODO_ITEM_REQUEST = 0;

    TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAdapter = new TaskAdapter(getApplicationContext());

        getListView().setFooterDividersEnabled(true);
        //TextView footerView = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.footer_view, null);
        //getListView().addFooterView(footerView);
        Button addBtn = (Button) findViewById(R.id.btnAddNew);
        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO - Attach Listener to FooterView. Implement onClick().
                Intent intent = new Intent(MainActivity.this, AddNewTask.class);

                // Start an Activity using that intent and the request code defined above
                startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);


            }
        });
        getListView().setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //log("Entered onActivityResult()");

        // TODO - Check result code and request code.
        // If user submitted a new ToDoItem
        // Create a new ToDoItem from the data Intent
        // and then add it to the adapter
        if (ADD_TODO_ITEM_REQUEST == requestCode && RESULT_OK == resultCode) {
            Task toDoItem = new Task(data);
            mAdapter.add(toDoItem);
        }

    }

    // Do not modify below here

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        // Load saved ToDoItems, if necessary
//
//        if (mAdapter.getCount() == 0)
//            loadItems();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        // Save ToDoItems
//
//        saveItems();
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//    private void dump() {
//
//        for (int i = 0; i < mAdapter.getCount(); i++) {
//            String data = ((ToDoItem) mAdapter.getItem(i)).toLog();
//            log("Item " + i + ": " + data.replace(ToDoItem.ITEM_SEP, ","));
//        }
//
//    }
//
//    // Load stored ToDoItems
//    private void loadItems() {
//        BufferedReader reader = null;
//        try {
//            FileInputStream fis = openFileInput(FILE_NAME);
//            reader = new BufferedReader(new InputStreamReader(fis));
//
//            String title = null;
//            String priority = null;
//            String status = null;
//            Date date = null;
//
//            while (null != (title = reader.readLine())) {
//                priority = reader.readLine();
//                status = reader.readLine();
//                date = ToDoItem.FORMAT.parse(reader.readLine());
//                mAdapter.add(new ToDoItem(title, Priority.valueOf(priority),
//                        Status.valueOf(status), date));
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != reader) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    // Save ToDoItems to file
//    private void saveItems() {
//        PrintWriter writer = null;
//        try {
//            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
//            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
//                    fos)));
//
//            for (int idx = 0; idx < mAdapter.getCount(); idx++) {
//
//                writer.println(mAdapter.getItem(idx));
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != writer) {
//                writer.close();
//            }
//        }
//    }

//    private void log(String msg) {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.i(TAG, msg);
//    }
}