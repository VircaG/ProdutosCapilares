package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BDTableCategory implements BaseColumns {
    public static final String FIELD_NOME = "nome";
    public static final String _ID = "ID";
    private SQLiteDatabase db;


    public BDTableCategory(SQLiteDatabase db){
        this.db = db;
    }
    public void create(){
        db.execSQL("CREATE TABLE category(" + FIELD_NOME + " TEXT NOT NULL," + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT)");
    }

}
