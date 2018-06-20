package com.example.virca.produtoscapilares;

import android.content.ContentProvider;
import android.content.UriMatcher;

public class ProdutosCapilaresContentProvider extends ContentProvider {

     BDProdutosCapilaresOpenHelper  bdProdutoCapilaresOpenHelper;




    private static UriMatcher getProdutosCapilares(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        }
        public boolean onCreate(){
        bdProdutoCapilaresOpenHelper  = new BDProdutosCapilaresOpenHelper(getContext());

        return true;
    }

}
