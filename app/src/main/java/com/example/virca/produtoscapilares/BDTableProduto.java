package com.example.virca.produtoscapilares;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Date;

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
  public   static ContentValues getContentValues(ProdutosCapilares produtosCapilares){
        ContentValues values = new ContentValues();


        values.put(_ID,produtosCapilares.getId());
          values.put(FIELD_NOME,produtosCapilares.getNome());
          values.put(FIELD_QUANTIDADE,produtosCapilares.getQuantidade());
          values.put(FIELD_ID_CATEGORY,produtosCapilares.getQuantidade());
          values.put(date,produtosCapilares.getDate());




  }
}
