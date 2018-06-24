package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDProdutosCapilaresOpenHelper extends SQLiteOpenHelper {
    private static final boolean PRODUCTION = false;

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

        if (!PRODUCTION){
            seed(db);
        }

    }
    private  void seed(SQLiteDatabase db){
       BDTableCategory bdTableCategory = new BDTableCategory(db);

        Category category = new Category();
        category.setNome("Shampoo");
        int idCategoryShampoo = (int) bdTableCategory.insert(BDTableCategory.getContentValues(category));


        category = new Category();
        category.setNome("Amaciador");
        int idCategoryAmaciador = (int) bdTableCategory.insert(BDTableCategory.getContentValues(category));


        category = new Category();
        category.setNome("Tonico");
        int  idCategoryTonico = (int) bdTableCategory.insert(BDTableCategory.getContentValues(category));

        BDTableProduto bdTableProduto = new BDTableProduto(db);

        ProdutosCapilares produtosCapilares = new ProdutosCapilares();
        produtosCapilares.setNome("Argan Oil of Marocco");
        produtosCapilares.setIdCategory(idCategoryShampoo);
        produtosCapilares.setQuantidade(5);
        produtosCapilares.setDate(12-06-18); //perguntar o professor
        bdTableProduto.insert(BDTableProduto.getContentValues(produtosCapilares));

        produtosCapilares = new ProdutosCapilares();
        produtosCapilares.setNome("Pro-Vitaminas Bomba Café");
        produtosCapilares.setIdCategory(idCategoryAmaciador);
        produtosCapilares.setQuantidade(5);
        produtosCapilares.setDate(12-06-18); //perguntar professor
        bdTableProduto.insert(BDTableProduto.getContentValues(produtosCapilares));


        produtosCapilares = new ProdutosCapilares();
        produtosCapilares.setNome("Puro Tónico Alho");
        produtosCapilares.setIdCategory(idCategoryTonico);
        produtosCapilares.setQuantidade(2);
        produtosCapilares.setDate(12-06-18); //perguntar o professor
        bdTableProduto.insert(BDTableProduto.getContentValues(produtosCapilares));





    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
