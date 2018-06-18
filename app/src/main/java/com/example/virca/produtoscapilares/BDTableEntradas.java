package com.example.virca.produtoscapilares;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BDTableEntradas implements BaseColumns {
    public static final String FIELD_QUANTIDADE = "quantidade";
    public static final String FIELD_DATA = "date";
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

        );

    }


    public static ContentValues getContentValues (Entrada entrada){
        ContentValues values = new ContentValues();

        values.put(_ID,entrada.getId());
        values.put(FIELD_DATA,entrada.getDate());
        values.put(FIELD_ID_PRODUTO,entrada.getId_produto());
        values.put(FIELD_QUANTIDADE,entrada.getQuantidade());


        return values;
    }
public static Entrada getCurrentCategoryFromCursor(Cursor cursor)
}
