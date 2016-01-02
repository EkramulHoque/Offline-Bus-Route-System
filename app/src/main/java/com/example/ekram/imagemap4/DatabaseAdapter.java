package com.example.ekram.imagemap4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by ekram on 7/31/2015.
 */
public class DatabaseAdapter extends SQLiteOpenHelper
{
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "BusV1.db";
    public final static String DATABASE_PATH ="/data/data/com.example.ekram.imagemap4/databases/";
    public static final int DATABASE_VERSION = 1;

    public DatabaseAdapter(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }


    public void createDatabase() throws IOException
    {
        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            Log.v("DB Exists", "db exists");

        }

        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
                Log.v("DB copied", "db copied");
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }
    }


    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }


    private void copyDataBase() throws IOException
    {
        InputStream inputStream=null;
        OutputStream outputStream=null;
        String dbFilePath= DATABASE_PATH+DATABASE_NAME;
        try{
            inputStream=myContext.getAssets().open(DATABASE_NAME);
            outputStream=new FileOutputStream(dbFilePath);
            byte []buffer=new byte[1024];
            int length;
            while ((length=inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
                Log.v("DB copied", "db copied");
            }
            Log.v("DB copied","db copied");
            Toast.makeText(myContext, "DB dump OK", Toast.LENGTH_LONG).show();
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch(IOException e){
            throw new Error("Problem copying Database from resource file");


        }
    }


    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }


    public void openDatabase() throws SQLException
    {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.v("DB Exists", "db opened");
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public void onCreate(SQLiteDatabase db)
    {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }
    }


}