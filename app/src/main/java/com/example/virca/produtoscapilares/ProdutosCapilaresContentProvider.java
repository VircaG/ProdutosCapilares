package com.example.virca.produtoscapilares;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ProdutosCapilaresContentProvider extends ContentProvider {

     BDProdutosCapilaresOpenHelper  bdProdutoCapilaresOpenHelper;




    private static UriMatcher getProdutosCapilares(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        }
        public boolean onCreate(){
        bdProdutoCapilaresOpenHelper  = new BDProdutosCapilaresOpenHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    private void notifyChanges (@NonNull Uri uri){
        getContext().getContentResolver().notifyChange(uri,null);
    }

}
