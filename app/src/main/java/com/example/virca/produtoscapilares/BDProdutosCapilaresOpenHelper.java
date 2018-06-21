package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDProdutosCapilaresOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ProdutosCapilares.db";
    public static final int DATABASE_VERSION = 1;


    public BDProdutosCapilaresOpenHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BDTableCategory bdTableCategory = new BDTableCategory(db);
        bdTableCategory.create();

        BDTableProduto bdTableProduto = new BDTableProduto(db);
        bdTableProduto.create();

        BDTableEntradas bdTableEntradas = new BDTableEntradas(db);
        bdTableEntradas.create();

        BDTableSaida bdTableSaida = new BDTableSaida(db);
        bdTableSaida.create();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
