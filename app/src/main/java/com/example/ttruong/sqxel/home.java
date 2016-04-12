package com.example.ttruong.sqxel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    private static int db_ver = 1;
    private static ArrayList<String> table_names = new ArrayList<String>();
    private static String temp_table_name, table_name;
    private static final int TABLE_NAME = 1,
                             ITEM_ADDED = 2,
                             EXPORT_TABLE = 3;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TABLE_NAME) {
            if(resultCode == RESULT_OK) {
                if(data.getExtras().containsKey("tableName"))
                {
                    temp_table_name = data.getStringExtra("tableName");
                    if(table_names.contains(temp_table_name))
                    {
                        table_name = temp_table_name;
                        table_names.add(table_name);
                        Toast.makeText(getApplicationContext(), "Table found!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        db_ver++;
                        table_name = temp_table_name;
                        table_names.add(table_name);
                        Toast.makeText(getApplicationContext(), "DB Version updated, " + temp_table_name + " added!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        else if(requestCode == ITEM_ADDED) {
            if(resultCode == RESULT_OK) {
                if(data.getExtras().containsKey("itemAdded"))
                {
                    Log.d("toastAdd", "Toast for entry add");
                    Toast.makeText(getApplicationContext(), "Entry Added to " + table_name + "!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(requestCode == EXPORT_TABLE)
        {
            if(resultCode == RESULT_OK) {
                if(data.getExtras().containsKey("tableExported"))
                {
                    String table_name = data.getStringExtra("tableExported");
                    Log.d("toastExport", "Toast for export");
                    Toast.makeText(getApplicationContext(), table_name + " exported!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void createTableButtonClicked(View view) {

        /* Instantiate Intent to switch to adding screen (activity) */
        Intent i = new Intent(this, createTable.class);

        /* Launch activity, Intent i contains which activity we want to switch to */
        startActivityForResult(i, TABLE_NAME);
    }

    public void addItemButtonClicked(View view) {

        if(table_name != null) {
        /* Instantiate Intent to switch to adding screen (activity) */
            Intent i = new Intent(this, addItem.class);
            i.putExtra("tableVer", db_ver);
            i.putExtra("tableName", table_name);

        /* Launch activity, Intent i contains which activity we want to switch to */
            startActivityForResult(i, ITEM_ADDED);
        }
        else
            Toast.makeText(getApplicationContext(), "Please create a table first!", Toast.LENGTH_SHORT).show();
    }

    public void exportButtonClicked(View view) {

        /* USER INPUT DIALOG ATTEMPT

        final EditText tempTable;
        LayoutInflater li = LayoutInflater.from(context);
        View tablePrompt = li.inflate(R.layout.table_prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(tablePrompt);

        final EditText tableInput = (EditText) tablePrompt.findViewById(R.id.tableInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tempTable.setText( tableInput.getText());

                            }
                        })
                        */


        Bundle tableNamesAry = new Bundle();
        tableNamesAry.putStringArrayList("tableNamesList", table_names);

        /* Instantiate Intent to switch to adding screen (activity) */
        Intent i = new Intent(this, export.class);
        i.putExtra("tableVer", db_ver);
        i.putExtras(tableNamesAry);

        /* Launch activity, Intent i contains which activity we want to switch to */
        startActivityForResult(i, EXPORT_TABLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
}
