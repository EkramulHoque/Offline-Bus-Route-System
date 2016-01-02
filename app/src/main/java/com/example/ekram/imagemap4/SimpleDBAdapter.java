package com.example.ekram.imagemap4;
import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * Created by ekram on 7/31/2015.
 */
public class SimpleDBAdapter {
    protected static final String TAG = SimpleDBAdapter.class.getName();

    private final Context mCtx;
    private SQLiteDatabase mDb;
    private DatabaseAdapter mDbHelper;


    public SimpleDBAdapter(Context ctx) {
        this.mCtx = ctx;
        mDbHelper = new DatabaseAdapter(mCtx);
    }
    public SimpleDBAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDatabase();
            Log.v(TAG, "database created");
        } catch (IOException ioe) {
            Log.v(TAG, ioe.toString() + "  Unable to create database.");
            throw new Error("Unable to create database");
        }
        return this;
    }

    public SimpleDBAdapter open() throws SQLException {

        try {

            mDbHelper.openDatabase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException sqle) {
            Log.v(TAG, sqle.toString());
            throw sqle;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    public String[] getEditTextValue(String from,String to) {

        Cursor s=mDb.rawQuery("select Bus_Name,Stop_Name from bus,rot,pit where bus.Route_ID = rot._id and rot.Stop_ID = pit._id and pit.Stop_Name ='"+from+"' COLLATE NOCASE and rot._id =(select rot._id from pit,rot where pit._id=rot.Stop_ID and pit.Stop_Name='"+to+"' COLLATE NOCASE);",null);
        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("Bus_Name"))+"/"+
                    s.getString(s.getColumnIndex("Stop_Name"));
            Log.v("First Name:", "" + values[i]);
            i++;
        }
        Log.v("log_tag","Count: " + s.getCount());
        s.close();
        return values;
    }
    public String[] getEditTextValueBus(String from,String to) {

        Cursor s=mDb.rawQuery("select Distinct pit.Stop_Name from bus,pit,rot where bus.Route_ID=rot._id and rot.Stop_ID=pit._id and bus.Bus_Name ='"+from+"' COLLATE NOCASE;",null);
        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("pit.Stop_Name")) ;
            Log.v("First Name:", "" + values[i]);
            i++;
        }
        Log.v("log_tag","Count: " + s.getCount());
        s.close();
        return values;
    }
    public String[] getEditTextValueStop(String from,String to) {

        Cursor s=mDb.rawQuery("select Distinct bus.Bus_Name from bus,pit,rot where bus.Route_ID=rot._id and rot.Stop_ID=pit._id and pit.Stop_Name ='"+from+"' COLLATE NOCASE;",null);
        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("bus.Bus_Name"))+"/"+

            Log.v("First Name:", "" + values[i]);
            i++;
        }
        Log.v("log_tag","Count: " + s.getCount());
        s.close();
        return values;
    }
    public String[] getEditTextValueBusDetails(String from,String to) {

        Cursor s=mDb.rawQuery("select Route_ID,Start,End from bus where Bus_Name='"+from+"' COLLATE NOCASE;",null);
        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("Route_ID"))+"/"+
                    s.getString(s.getColumnIndex("Start"))+"/"+s.getString(s.getColumnIndex("End"));
            i++;
        }
        Log.v("log_tag","Count: " + s.getCount());
        s.close();
        return values;
    }
    public String[] betweenStops(String from,String to) {

        Cursor s=mDb.rawQuery("select Distinct Bus_Name,Route_ID from bus  where  \n" +
                " bus.Route_ID IN\n" +
                "(select a._id from\n" +
                "(select rot._id from rot,pit where rot.Stop_ID=pit._id and pit.Stop_Name='"+to+"' COLLATE NOCASE) as a\n" +
                "join\n" +
                "(select rot._id from rot,pit where rot.Stop_ID=pit._id and pit.Stop_Name='"+from+"'COLLATE NOCASE ) as b\n" +
                "on a._id=b._id)",null);

        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("Bus_Name"))+"/"+
                    s.getString(s.getColumnIndex("Route_ID"));
            i++;
        }

        s.close();
        return values;
    }
    public String[] OnlyRoute(String from,String to) {

        Cursor s=mDb.rawQuery("select Distinct Route_ID from bus  where  \n" +
                " bus.Route_ID IN\n" +
                "(select a._id from\n" +
                "(select rot._id from rot,pit where rot.Stop_ID=pit._id and pit.Stop_Name='"+to+"' COLLATE NOCASE) as a\n" +
                "join\n" +
                "(select rot._id from rot,pit where rot.Stop_ID=pit._id and pit.Stop_Name='"+from+"'COLLATE NOCASE ) as b\n" +
                "on a._id=b._id)",null);

        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("Route_ID"));
            i++;
        }

        s.close();
        return values;
    }
    public String[] OnlyStop(String from,String to,int r) {

        Cursor s=mDb.rawQuery("select val.Stop_Name from val where val.weight between(\n" +
                "select min(val.weight)from val where val.weight in( select val.weight from val where val._id="+r+" and val.Stop_Name='"+to+"' COLLATE NOCASE \n" +
                "Union select val.weight from val where val._id="+r+" and val.Stop_Name='"+from+"' COLLATE NOCASE)) AND (\n" +
                "select max(val.weight)from val where val.weight in( select val.weight from val where val._id="+r+" and val.Stop_Name='"+to+"' COLLATE NOCASE \n" +
                "Union select val.weight from val where val._id="+r+" and val.Stop_Name='"+from+"' COLLATE NOCASE))\n",null);

        String[] values = new String[s.getCount()];
        int i = 0;
        for (s.moveToFirst(); !s.isAfterLast(); s.moveToNext()) {
            values[i] = s.getString(s.getColumnIndex("Stop_Name"));
            i++;
        }

        s.close();
        return values;
    }


}

