package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BDTableEntradas implements BaseColumns {
    public static final String FIELD_QUANTIDADE = "quantidade";
    public static final String FIELD_DATA = "data";
    public static final String FIELD_ID_PRODUTO = "id_produto";
    public static final String TABLE_NAME = "entrada";

    private SQLiteDatabase db;

    public BDTableEntradas (SQLiteDatabase db){
        this.db = db;
    }
    public void create(){
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FIELD_ID_PRODUTO + " INTENGER REFERENCES " + BDTableProduto.TABLE_NAME + "(" + BDTableProduto._ID + ")," +
                        FIELD_QUANTIDADE + " INTENGER," +
                        FIELD_DATA + " TEXT" +
                        ")"
//                "CREATE TABLE " + TABLE_NAME + "(" +
//                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
//                        DATE + "TEXT NOT NULL," +
//                        FIELD_QUANTIDADE_PRO + "INTENGER," +
//                        FIELD_ID_PRODUTO + " INTEGER,"  +
//
//
//                        "FOREIGN KEY ("+ FIELD_ID_PRODUTO +") REFERENCES " +
//                        BDTableProduto.TABLE_NAME +
//                                "("+BDTableProduto._ID_PRODUTO+ ")"
        );

    }

}
