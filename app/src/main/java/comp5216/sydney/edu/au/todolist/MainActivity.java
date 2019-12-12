package comp5216.sydney.edu.au.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    ListView listview;
    //ArrayList<String> items;
    //ArrayAdapter<String> itemsAdapter;
    ArrayList<User> arrayOfUsers;//=new ArrayList<User>();
    UsersAdapter adapter;//=new UsersAdapter(this, arrayOfUsers);
    EditText addItemEditText;
    public final int EDIT_ITEM_REQUEST_CODE=647;


    //attaching Adapter to a listView
    //ArrayList<User> arrayOfUsers=new ArrayList<>();
    //UsersAdapter adapter=new UsersAdapter(this, arrayOfUsers);
    //listview=(ListView)findViewById(R.id.editText);

    //listview=(ListView) findViewById(R.id.listView);
    //listview.setAdapter(adapter);




    @Override
    protected void onCreate(Bundle savedInstanceState) {//recalled when the activity firstly created
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayOfUsers=new ArrayList<User>();
        adapter=new UsersAdapter(this, arrayOfUsers);
        listview=(ListView) findViewById(R.id.listView);
        //listview.setAdapter(adapter);
        listview.setAdapter(adapter);
        addItemEditText=(EditText)findViewById(R.id.editText);

        //items=new ArrayList<String>();
        //items.add("items one");
        //items.add("items two");

        //itemsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //listview.setAdapter(itemsAdapter);

        //
        //ListView listView=(ListView)findViewById(R.id.editText);

        //ArrayList<User> arrayOfUsers=new ArrayList<User>();




        setupListViewListener();

    }

    public void onAddItemClick(View view){
        String toAddString=addItemEditText.getText().toString();
        User user=new User(toAddString);
        if(toAddString!=null&&toAddString.length()>0){
            //UsersAdapter adapter=new UsersAdapter(this, arrayOfUsers);
            //adapter.add(toAddString);
            adapter.add(user);
            addItemEditText.setText("");
        }
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

    private void setupListViewListener(){
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //UsersAdapter adapter=new UsersAdapter(this, arrayOfUsers);

                Log.i("MainActivity", "Long Clicked item" + position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//create a dialog?
                //builder.setTitle(R.string.dialog_delete_title).setMessage(R.string.dialog_delete_msg+" "+items.get(position)).setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                builder.setTitle(R.string.dialog_delete_title).setMessage(getResources().getString(R.string.dialog_delete_msg)+" "+arrayOfUsers.get(position).getName()+"?").setPositiveButton(R.string.delete, new DialogInterface.OnClickListener(){
                public void onClick (DialogInterface dialog,int id){
                    arrayOfUsers.remove(position);
                    //items.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }

            ).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick (DialogInterface dialog,int id){

                }
            }

            );
            builder.create().show();

            return true;
        }
    });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String updateItem = (String) adapter.getItem(position).getName();
                Log.i("MainActivity", "Clicked item" + position + ":" + updateItem);

                Intent intent=new Intent(MainActivity.this, EditToDoItemActivity.class);
                if(intent!=null){
                    intent.putExtra("item",position);
                    intent.putExtra("position",position);

                    startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    protected void onActivetyResult(int requestCode, int resultCode, Intent data){
        if(requestCode==EDIT_ITEM_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                String editedItem=data.getExtras().getString("item");
                User user=new User(editedItem);
                int position=data.getIntExtra("position",-1);
                //items.set(position,editedItem);
                arrayOfUsers.set(position, user);
                Log.i("Updated Item in list:", editedItem + ",position:" + position);

                Toast.makeText(this, "updated:"+editedItem, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }
    }
}