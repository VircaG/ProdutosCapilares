package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDProdutosCapilares extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ProdutosCapilares.db";
    public static final int VERSION = 1;


    public BDProdutosCapilares(Context context) {
        super(context, DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BDTableCategory bdTableCategory = new BDTableCategory(db);
        bdTableCategory.create();
        BDTableProduto bdTableProduto = new BDTableProduto(db);
        bdTableProduto.create();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
