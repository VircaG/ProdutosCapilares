package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DbTableSaida implements BaseColumns {
    private SQLiteDatabase db;

    public DbTableSaida(SQLiteDatabase db){
        this.db = db;
    }
    public void creat(){
        db.execSQL("CREATE TABLE saida("data);
    }
}
