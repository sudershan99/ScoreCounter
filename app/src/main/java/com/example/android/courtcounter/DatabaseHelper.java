package com.example.android.courtcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Divya Sudershan on 28-07-2020.
 */

public class DatabaseHelper extends SQLiteOpenHelper{


    public static final String Database_name = "ScoreTable.db";
    public static final String Table_name = "Score_Table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "Team A";
    public static final String Col_3 = "Team B";

    public DatabaseHelper(Context context) {
        super(context, Database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Score_Table(ID INTEGER PRIMARY KEY AUTOINCREMENT, TEAMA TEXT, TEAMB TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String drop = "DROP TABLE IF EXISTS Score_Table";

        db.execSQL(drop);
        onCreate(db);

    }
    public boolean insertData(String team_A , String team_B)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEAMA" , team_A);
        contentValues.put("TEAMB" , team_B);
        long result = db.insert("Score_Table" , null , contentValues);
        if(result == -1)
            return false;

        else
            return true;
    }

    public Cursor getAlldata()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Score_Table",null);
        return res;
    }

    public int deletedata(String id)
    {

        SQLiteDatabase db = this.getWritableDatabase();
       return  db.delete("Score_Table" , "ID = ?", new String[] {id});

    }
}
