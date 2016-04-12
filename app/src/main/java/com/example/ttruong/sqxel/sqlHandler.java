package com.example.ttruong.sqxel;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class sqlHandler extends SQLiteOpenHelper {

    /* Database title */
    private static final String DB_NAME = "test.db";
    private static int DB_VER;

    /* Table title will be determined by the user */
    private static String TABLE_NAME;

    private boolean mExtStorageAvailable = false,
                    mExtStorageWriteable = false;

    /* Services column */
    public static final String COLUMN_LOCAL = "local",
            COLUMN_DATE = "date",
            COLUMN_UNIT_NUM = "UnitNum",
            COLUMN_VIN_NUM = "VinNum",
            COLUMN_LICENSE_NUM = "LicenseNum",
            COLUMN_MILEAGE = "Mileage",
            COLUMN_CODE = "ClaimNum",
            COLUMN_YEAR = "Year",
            COLUMN_MAKE = "Make",
            COLUMN_MODEL = "Model",
            COLUMN_COLOR = "Color",
            COLUMN_WORK_DONE = "Work",
            COLUMN_PRICE = "Price";

    /* Ctor - will also establish table name */
    public sqlHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String tableName) {
        super(context, DB_NAME, factory, version);

        TABLE_NAME = tableName;
        DB_VER = version;
        Log.d("ctor", "Inside sqlctor");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("onCreate", "Inside onCreate");
        Log.d("onCreatever", "db_ver is " + DB_VER);

        /* Query to be create table Inventory w/specified columns */
        String query = "CREATE TABLE " + TABLE_NAME + "" +
                "(" +
                COLUMN_LOCAL + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_UNIT_NUM + " TEXT PRIMARY KEY," +
                COLUMN_VIN_NUM + " TEXT, " +
                COLUMN_LICENSE_NUM + " TEXT, " +
                COLUMN_MILEAGE + " INTEGER, " +
                COLUMN_CODE + " TEXT, " +
                COLUMN_YEAR + " INTEGER, " +
                COLUMN_MAKE + " TEXT, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_COLOR + " TEXT, " +
                COLUMN_WORK_DONE + " TEXT, " +
                COLUMN_PRICE + " INTEGER " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("onUpgrade", "Inside onUpgrade");
        onCreate(db);
    }

    private boolean checkExtMedia() {
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            mExtStorageAvailable = mExtStorageWriteable = true;
        }
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            mExtStorageAvailable = true;
            mExtStorageWriteable = false;
        }
        else
            mExtStorageAvailable = mExtStorageWriteable = false;

        return (mExtStorageAvailable && mExtStorageWriteable);
    }

    public void writeToFile() {
        if(checkExtMedia() == true) {
            Log.d("mediaCheck", "Ext exists!");
            File root = Environment.getExternalStorageDirectory();
            Log.d("root", root.toString());

            File dir = new File(root.getAbsolutePath() + "/Downloads");
            dir.mkdirs();
            File file = new File(dir, TABLE_NAME + ".csv");

            try {

                FileOutputStream f = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(f);

                String columnHeader = COLUMN_LOCAL + ", " +
                        COLUMN_DATE + ", " +
                        COLUMN_UNIT_NUM + ", " +
                        COLUMN_VIN_NUM + ", " +
                        COLUMN_LICENSE_NUM + ", " +
                        COLUMN_MILEAGE + ", " +
                        COLUMN_CODE + ", " +
                        COLUMN_YEAR + ", " +
                        COLUMN_MAKE + ", " +
                        COLUMN_MODEL + ", " +
                        COLUMN_COLOR + ", " +
                        COLUMN_WORK_DONE + ", " +
                        COLUMN_PRICE;

                pw.println(columnHeader);

        /* String to hold database results */
                String dbString = "",
                        query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        /* Current database */
                SQLiteDatabase db = getWritableDatabase();

        /* Cursor/ptr points to location in table */
                Cursor c = db.rawQuery(query, null);

        /* Move to 1st row in table */
                c.moveToFirst();

        /* Appending data */
                while (!c.isAfterLast()) {
                    /*** GATHER ALL DATA FROM EVERY COLUMN OF 1 ROW */
                    if (c.getString(c.getColumnIndex(COLUMN_LOCAL)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_LOCAL));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_DATE)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_DATE));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_UNIT_NUM)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_UNIT_NUM));
                        dbString += ", ";
                    }


                    if (c.getString(c.getColumnIndex(COLUMN_VIN_NUM)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_VIN_NUM));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_LICENSE_NUM)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_LICENSE_NUM));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_MILEAGE)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_MILEAGE));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_CODE)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_CODE));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_YEAR)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_YEAR));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_MAKE)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_MAKE));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_MODEL)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_MODEL));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_COLOR)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_COLOR));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_WORK_DONE)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_WORK_DONE));
                        dbString += ", ";
                    }

                    if (c.getString(c.getColumnIndex(COLUMN_PRICE)) != null) {
                        dbString += c.getString(c.getColumnIndex(COLUMN_PRICE));
                    }

                    pw.println(dbString);

                    dbString += "\n";
                    c.moveToNext();
                }

                pw.flush();
                pw.close();
                f.close();
            /* Closure */
                db.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("FileNotFound", "File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            Log.d("Extfail", "There is no ext!");
    }

    /* Insert row into database */
    public void addItem(item items) {

        /* Allows changing data for columns & insert all easily */
        ContentValues values = new ContentValues();

        /* Insert data for each column */
        values.put(COLUMN_LOCAL, items.getLocalCode());
        values.put(COLUMN_DATE, items.getDate());
        values.put(COLUMN_UNIT_NUM, items.getUnitNum());
        values.put(COLUMN_VIN_NUM, items.getVinNum());
        values.put(COLUMN_LICENSE_NUM, items.getLicenseNum());
        values.put(COLUMN_MILEAGE, items.getMileage());
        values.put(COLUMN_CODE, items.getCode());
        values.put(COLUMN_YEAR, items.getYear());
        values.put(COLUMN_MAKE, items.getMake());
        values.put(COLUMN_MODEL, items.getModel());
        values.put(COLUMN_COLOR, items.getColor());
        values.put(COLUMN_WORK_DONE, items.getWorkDone());
        values.put(COLUMN_PRICE, items.getPrice());

        Log.d("addItem", "Inside addItem");

        /* Get current database */
        SQLiteDatabase db = getWritableDatabase();

        /* Insert row */
        db.insert(TABLE_NAME, null, values);

        /* Close database */
        db.close();
    }
}

