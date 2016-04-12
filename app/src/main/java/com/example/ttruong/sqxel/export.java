package com.example.ttruong.sqxel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class export extends AppCompatActivity {
    private static EditText inputField;
    private static TextView promptText;
    private static int dbVer;
    private static ArrayList<String> table_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
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

         /* Input field & prompt text of top section */
        inputField = (EditText) findViewById(R.id.inputField);
        promptText = (TextView) findViewById(R.id.promptText);

        Bundle dbData = getIntent().getExtras();
        if(dbData == null) {
            Toast.makeText(getApplicationContext(), "DB Ver not received!", Toast.LENGTH_SHORT).show();
            return;
        }

        table_names = dbData.getStringArrayList("tableNamesList");

        this.dbVer = dbData.getInt("tableVer");
    }

    public void exportButtonClicked(View view) {

        String tableName = inputField.getText().toString();
        if(table_names.contains(tableName))
        {
            sqlHandler db = new sqlHandler(this, null, null, this.dbVer, tableName);
            db.writeToFile();
            Intent output = getIntent();
            output.putExtra("tableExported", tableName);
            setResult(RESULT_OK, output);
            finish();
        }
        else
            Toast.makeText(getApplicationContext(), "Table not found!", Toast.LENGTH_SHORT).show();
    }

}
