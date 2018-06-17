package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Calendar;

public class DbTableSaida implements BaseColumns {
    public static final String TABLE_NAME = "saida";
    public static final String FIELD_DATA = "data";
    public static final String FIELD_ID_PRODUTO = "id_produto";
    private SQLiteDatabase db;

    public DbTableSaida(SQLiteDatabase db){
        this.db = db;
    }
    public void creat(){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_DATA +"TEXT ," +
                FIELD_ID_PRODUTO+ " INTEGER,"+
                "FOREIGN KEY(" + FIELD_ID_PRODUTO + ") REFERENCES " + BDTableProduto.TABLE_NAME+
                "(" + BDTableProduto._ID + ")" +")"
        );


    }
}
