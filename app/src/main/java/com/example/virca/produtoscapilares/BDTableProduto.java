package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BDTableProduto implements BaseColumns {
    public static final String FIELD_ID_CATEGORY = "idcategory";
    public static final String FIELD_QUANTIDADE = "QUANTIDADE";
    public static final String FIELD_NOME = "NOME";
    public static final String TABLE_NAME = "PRODUTOS";
    private SQLiteDatabase db;

    public BDTableProduto (SQLiteDatabase db){
        this.db = db;
    }
    public void create (){

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_NOME + " TEXT NOT NULL," +
                FIELD_QUANTIDADE + " INTENGER," +
                FIELD_ID_CATEGORY + " INTEGER REFERENCES " + BDTableCategory.TABLE_NAME + "(" + BDTableCategory._ID + ")" +
                ")");
    }
}
