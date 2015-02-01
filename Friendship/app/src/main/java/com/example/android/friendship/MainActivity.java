package com.example.android.friendship;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends ListActivity {
    private ListView listView;
    private Button btnAddNew;
    private Button button;

    private static final String FILE_NAME = "save_data.txt";
    private static final String TAG = "Lab-UserInterface";

    public static final int ADD_TODO_ITEM_REQUEST = 0;
    public static final int EDIT_TASK_REQUEST = 1;
    public static TaskAdapter mAdapter;
    public static final String ACCOUNT_SID = "AC2160f09796116debaca44e91ed64f0a8";
    public static final String AUTH_TOKEN = "0607fc4fb4204b86ec93cd41c5bc8727";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAdapter = new TaskAdapter(getApplicationContext());
        loadItems();
        getListView().setFooterDividersEnabled(true);
        ListView list = getListView();
        Button addBtn = (Button) findViewById(R.id.btnAddNew);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//
//                // Build a filter for the MessageList
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
//                params.add(new BasicNameValuePair("To", "+16475234160"));
//                params.add(new BasicNameValuePair("From", "+16476910648"));
//
//                MessageFactory messageFactory = client.getAccount().getMessageFactory();
//                com.twilio.sdk.resource.instance.Message message = null;
//                try {
//                    message = messageFactory.create(params);
//                } catch (TwilioRestException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(message.getSid());
//            }
//        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO - Attach Listener to FooterView. Implement onClick().
                Intent intent = new Intent(MainActivity.this, AddNewTask.class);

                // Start an Activity using that intent and the request code defined above
                startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);


            }
        });
        getListView().setAdapter(mAdapter);
        list.setTextFilterEnabled(true);
        list.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //log("I got dicks in my buttttt");
//                Intent intent = new Intent(MainActivity.this, EditTask.class);
//                intent.putExtra("position",pos);
//                // Start an Activity using that intent and the request code defined above
//                startActivityForResult(intent, EDIT_TASK_REQUEST);
            }
        });


    }
//    private class DownloadTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            try {
//                return loadFromNetwork(urls[0]);
//            } catch (IOException e) {
//                return "shit nigga";
//            }
//        }
//
//        /**
//         * Uses the logging framework to display the output of the fetch
//         * operation in the log fragment.
//         */
//        @Override
//        protected void onPostExecute(String result) {
//            Log.i(TAG, result);
//        }
//    }


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
        else if (EDIT_TASK_REQUEST == requestCode && RESULT_OK == resultCode) {
            Task toDoItem = new Task(data);
            int pos = Integer.parseInt(data.getStringExtra("position"));
            mAdapter.update(toDoItem,pos);
        }

    }

    public void sendIntent(int pos) {

        Intent intent = new Intent(MainActivity.this, EditTask.class);
        intent.putExtra("position",pos);

        startActivityForResult(intent, EDIT_TASK_REQUEST);

    }
    //Do not modify below here
    @Override
    public void onStart() {
        super.onStart();

        // Load saved ToDoItems, if necessary
       // log("start");


    }
    public void onRestart() {
        super.onRestart();

        // Load saved ToDoItems, if necessary
        //log("restart");

    }
    @Override
    public void onResume() {
        super.onResume();

        // Load saved ToDoItems, if necessary
        //log("resume");
        if (mAdapter.getCount() == 0){
            loadItems();}
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save ToDoItems

        saveItems();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Save ToDoItems

        saveItems();

    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save ToDoItems

        saveItems();

    }
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
    private void dump() {

        for (int i = 0; i < mAdapter.getCount(); i++) {
            String data = ((Task) mAdapter.getItem(i)).toLog();
            log("Item " + i + ": " + data.replace(Task.ITEM_SEP, ","));
        }

    }

    // Load stored ToDoItems
    private void loadItems() {
        //JSONParser parser = new JSONParser();
        BufferedReader reader = null;
        try{
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            Boolean status = null;
            String date = null;
            String points = null;

            String temp = reader.readLine();
            while(temp!=null){
                log("Reading: "+temp);

                title = temp;
                status = Boolean.parseBoolean(reader.readLine());
                date = reader.readLine();
                points = reader.readLine();

                mAdapter.add(new Task(title,status,date,points));
                temp = reader.readLine();

            }
            mAdapter.notifyDataSetChanged();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();

        }finally{
            if (null!=reader){
                try{
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    // Save ToDoItems to file
    private void saveItems() {

        PrintWriter writer = null;

        try{
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)));

            for (int idx = 0; idx<mAdapter.getCount(); idx++){
                Task task = mAdapter.getItem(idx);
                writer.println(task.getTitle());
                writer.println(task.getStatus());
                writer.println(task.getDate());
                writer.println(task.getPoints());
//                log("WTitle: "+task.getTitle());
//
//                log("Wstatus: "+task.getStatus());
//
//                log("WDate: "+task.getDate());

            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(null!=writer){
                writer.close();
            }
        }
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