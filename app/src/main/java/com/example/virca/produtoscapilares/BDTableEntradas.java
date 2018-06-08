package com.example.virca.produtoscapilares;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Date;

public class BDTableEntradas implements BaseColumns {
    public static final String TABLE_NAME = "entrada";
    public static final String FIELD_QUANTIDADE = "quantidade";
    public static final String DATE = "date";
    private SQLiteDatabase db;

    public BDTableEntradas (SQLiteDatabase db){
        this.db = db;
    }
    public void create(){
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "("+ DATE + "TEXT NOT NULL," +
                        FIELD_QUANTIDADE+ "INTENGER," +
                        FIELD_QUANTIDADE +"INTENGER,FOREIGN KEY " +
                         (" + quantidade +") REFERENCES " +" +
                            " BDTableProduto.TABLE_NAME +" +
                                "("+BDTableProduto._ID + ")" +")"
        );

    }

}
