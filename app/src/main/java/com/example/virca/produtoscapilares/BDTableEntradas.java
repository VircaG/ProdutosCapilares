package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BDTableEntradas implements BaseColumns {
    private SQLiteDatabase db;

    public BDTableEntradas (SQLiteDatabase db){
        this.db = db;
    }
    public void create(){
        db.execSQL("CREATE TABLE entrada(ID INTEGER,FOREIGN KEY," +
                "REFENCES  )");

    }

}
