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

public class addItem extends AppCompatActivity {

    sqlHandler db;

    private String local = "8v",
            date,
            unitNum,
            vinNum,
            licenseNum,
            code = "",
            make,
            model,
            color,
            workDone,
            tableName;

    private int mileage,
            year,
            price,
            dbVer;

    private static int ctr = 0;

    private static EditText inputField;
    private static TextView promptText;
    private static TextView tableNameText;

    private static final String TABLE_NAME_PROMPT = "Please enter the table name.",
            DATE_PROMPT = "Please enter today's date.",
            UNIT_NUM_PROMPT = "Please enter the vehicle's unit number.",
            VIN_NUM_PROMPT = "Please enter the vehicle's VIN number.",
            LICENSE_NUM_PROMPT = "Please enter the vehicle's license plate number",
            MILEAGE_PROMPT = "Please enter the vehicle's mileage.",
            CODE_PROMPT = "Please enter the code or claim number.",
            YEAR_PROMPT = "Please enter the vehicle's year of manufacturing.",
            MAKE_PROMPT = "Please enter the vehicles make.",
            MODEL_PROMPT = "Please enter the vehicle's model.",
            COLOR_PROMPT = "Please enter the vehicle's color.",
            WORK_DONE_PROMPT = "Please enter the work done for the vehicle.",
            PRICE_PROMPT = "Please enter the price of the work done.",
            REVIEW_PROMPT = "Please review that the information is correct.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
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
        tableNameText = (TextView) findViewById(R.id.tableNameText);

        Bundle dbData = getIntent().getExtras();
        if(dbData == null) {
            return;
        }

        this.dbVer = dbData.getInt("tableVer");
        this.tableName = dbData.getString("tableName");

        tableNameText.setText(tableName);
        promptText.setText(DATE_PROMPT);

    }

    public void submitButtonClicked(View view) {

        /* Cycle to next prompt */
        if (this.ctr < 13)
            enterData(inputField.getText().toString());

    }

    public void enterData(String data) {

        switch(this.ctr++)
        {
            case 0:
                db = new sqlHandler(this, null, null, this.dbVer, this.tableName);
                date = data;

                /* Prompt the user for the date */
                promptText.setText(UNIT_NUM_PROMPT);
                break;

            case 1:
                unitNum = data;

                promptText.setText(VIN_NUM_PROMPT);
                break;

            case 2:
                vinNum = data;

                promptText.setText(LICENSE_NUM_PROMPT);
                break;

            case 3:
                licenseNum = data;

                promptText.setText(MILEAGE_PROMPT);
                break;

            case 4:
                int mileageTemp = Integer.parseInt(data);

                mileage = mileageTemp;

                promptText.setText(YEAR_PROMPT);
                break;

            case 5:
                int yearTemp = Integer.parseInt(data);

                year = yearTemp;

                promptText.setText(MAKE_PROMPT);
                break;

            case 6:
                make = data;

                promptText.setText(MODEL_PROMPT);
                break;

            case 7:
                model = data;

                promptText.setText(COLOR_PROMPT);
                break;

            case 8:
                color = data;

                promptText.setText(WORK_DONE_PROMPT);
                break;

            case 9:
                workDone = data;

                promptText.setText(PRICE_PROMPT);
                break;

            case 10:
                int priceTemp = Integer.parseInt(data);

                price = priceTemp;

                promptText.setText(REVIEW_PROMPT);
                break;

            case 11:

                /* After user guarantees inputted data is correct, instantiate service object */
                item entry = new item(local, date, unitNum, vinNum, licenseNum, code, make,
                        model, color, workDone, mileage, year, price);

                /* Add entry onto the table */
                db.addItem(entry);

                Intent output = getIntent();
                output.putExtra("itemAdded", "we did it boyz");
                setResult(RESULT_OK, output);
                finish();
                break;

            default:
                break;
        }

        /* Reset inputfield */
        inputField.setText("");
    }
}
