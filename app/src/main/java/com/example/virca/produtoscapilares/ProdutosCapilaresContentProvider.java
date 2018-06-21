package com.example.virca.produtoscapilares;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ProdutosCapilaresContentProvider extends ContentProvider {

    private static  final String AUTHORITY ="com.example.virca.produtoscapilares";


    public static final int PRODUTOSCAPILARES = 100;
    public static final int PRODUTOSCAPILARES_ID = 101;
    public static final int CATEGORY = 200;
    public static final int CATEGORY_ID = 201;
    public static final int ENTRADA = 300;
    public static final int ENTRADA_ID = 301;
    public static final int SAIDA = 400;
    public static final int SAIDA_ID = 401;


    private static final String MULTIPLE_ITEMS = "vnd.android.cursor.dir";
    private  static  final String SINGLE_INTEM = "vnd.android.cursor.item";

    BDProdutosCapilaresOpenHelper  bdProdutoCapilaresOpenHelper;





    private static UriMatcher getProdutosCapilaresUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY,"produtosCapilares",PRODUTOSCAPILARES);
        uriMatcher.addURI(AUTHORITY,"produtosCapilares/#", PRODUTOSCAPILARES_ID);

        uriMatcher.addURI(AUTHORITY,"category", CATEGORY);
        uriMatcher.addURI(AUTHORITY,"category/#", CATEGORY_ID);

        uriMatcher.addURI(AUTHORITY,"entrada", ENTRADA);
        uriMatcher.addURI(AUTHORITY,"entrada/#", ENTRADA_ID);

        uriMatcher.addURI(AUTHORITY,"saida", SAIDA);
        uriMatcher.addURI(AUTHORITY,"saida/#", SAIDA_ID);

        return uriMatcher;

        }
        public boolean onCreate(){
        bdProdutoCapilaresOpenHelper  = new BDProdutosCapilaresOpenHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = bdProdutoCapilaresOpenHelper.getReadableDatabase();

        String id = uri.getLastPathSegment();

        UriMatcher uriMatcher = getProdutosCapilaresUriMatcher();

        switch (uriMatcher.match(uri)){
            case PRODUTOSCAPILARES:
                return  new BDTableProduto(db).query(projection,selection,selectionArgs,null,null,sortOrder);

            case CATEGORY:
                return new BDTableCategory(db).query(projection,selection,selectionArgs,null,null,sortOrder);

            case ENTRADA:
                return  new BDTableEntradas(db).query(projection,selection,selectionArgs,null,null,sortOrder);

            case SAIDA:
                return  new BDTableSaida(db).query(projection,selection,selectionArgs,null,null,sortOrder);


            case PRODUTOSCAPILARES_ID:
                return  new BDTableProduto(db).query(projection,BDTableProduto._ID + "?", new String[] {id},null,null,null);

            case CATEGORY_ID:
                return  new  BDTableCategory(db).query(projection,BDTableCategory._ID +"?",new String[] {id},null,null,sortOrder);

            case ENTRADA_ID:
                return new BDTableEntradas(db).query(projection,BDTableEntradas._ID + "?",new String[] {id},null,null,sortOrder);

            case SAIDA_ID:
                return  new BDTableSaida(db).query(projection,BDTableSaida._ID + "?",new String[]   {id},null,null,sortOrder);

                default:
                    throw new UnsupportedOperationException("URI Invalido:" + uri);



        }




    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        UriMatcher matcher = getProdutosCapilaresUriMatcher();

        switch (matcher.match(uri)) {

            case PRODUTOSCAPILARES:
                return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + BDTableProduto.TABLE_NAME;

            case CATEGORY:
                return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + BDTableCategory.TABLE_NAME;

            case ENTRADA:
                return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + BDTableEntradas.TABLE_NAME;
            case SAIDA:
                return MULTIPLE_ITEMS + "/" + AUTHORITY + "/" + BDTableSaida.TABLE_NAME;


            case PRODUTOSCAPILARES_ID:
                return SINGLE_INTEM +"/" + AUTHORITY + "/" + BDTableProduto.TABLE_NAME;


            case CATEGORY_ID:
                return SINGLE_INTEM + "/" + AUTHORITY + "/" + BDTableCategory.TABLE_NAME;


            case ENTRADA_ID:
                return ENTRADA_ID + "/" +  AUTHORITY + "/" + BDTableEntradas.TABLE_NAME;

            case SAIDA_ID :
                return SAIDA_ID + "/" + AUTHORITY + "/" + BDTableSaida.TABLE_NAME;


                default:
                throw new UnsupportedOperationException("URI Desconhecido: " + uri);
        }


    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = bdProdutoCapilaresOpenHelper.getWritableDatabase();

        UriMatcher matcher = getProdutosCapilaresUriMatcher();
        long id = -1;

        switch (matcher.match(uri)) {
            case PRODUTOSCAPILARES:
                id = new BDTableProduto(db).insert(values);
                break;

            case CATEGORY:
                id = new BDTableCategory(db).insert(values);
                break;


            case ENTRADA:
                id = new BDTableEntradas(db).insert(values);
                break;

            case SAIDA:
                id = new BDTableSaida(db).insert(values);
                break;


            default:
                throw new UnsupportedOperationException("URI Invalido:" + uri);

        }

        if (id > 0) {
            notifyChanges(uri);
            return Uri.withAppendedPath(uri, Long.toString(id));
        } else {
            throw new SQLException("Não foi possível inserir o registro");

        }
    }


    private void notifyChanges (@NonNull Uri uri){
        getContext().getContentResolver().notifyChange(uri,null);
    }

    public int delete(@NonNull Uri uri,@Nullable String selection,@Nullable String[] selectionArgs){
        SQLiteDatabase db = bdProdutoCapilaresOpenHelper.getWritableDatabase();

        UriMatcher matcher = getProdutosCapilaresUriMatcher();

        String id = uri.getLastPathSegment();

        int rows = 0;

        switch (matcher.match(uri)){
            case PRODUTOSCAPILARES_ID:
                rows = new BDTableProduto(db).delete(BDTableProduto._ID + "=?",new String[] {id});
                break;

            case CATEGORY_ID:
                 rows = new BDTableCategory(db).delete(BDTableCategory._ID + "=?",new String[] {id});
                 break;

            case ENTRADA_ID:
                rows = new BDTableEntradas(db).delete(BDTableEntradas._ID + "=?",new String[] {id});
                break;

            case SAIDA_ID:
                rows = new BDTableSaida(db).delete(BDTableSaida._ID +"?",new String[] {id});
                break;

                default:
                    throw new UnsupportedOperationException("Uri Inválido:" + uri);

        }
        if(rows > 0) notifyChanges(uri);

        return rows;


    }

   public int update(@NonNull Uri uri,@Nullable ContentValues values,@Nullable String selection,@Nullable String[] selectionArgs) {
       SQLiteDatabase db = bdProdutoCapilaresOpenHelper.getWritableDatabase();

       UriMatcher matcher = getProdutosCapilaresUriMatcher();

       String id = uri.getLastPathSegment();

       int rows = 0;
       switch (matcher.match(uri)) {
           case PRODUTOSCAPILARES_ID:
               rows = new BDTableProduto(db).update(values, BDTableProduto._ID + "?", new String[]{id});
               break;


           case CATEGORY_ID:
               rows = new BDTableCategory(db).update(values, BDTableProduto._ID + "?", new String[]{id});
               break;


           case ENTRADA_ID:
               rows = new BDTableEntradas(db).update(values, BDTableEntradas._ID + "?", new String[]{id});
               break;


           case SAIDA_ID:
               rows = new BDTableSaida(db).update(values, BDTableSaida._ID + "?", new String[]{id});
               break;


           default:
               throw new UnsupportedOperationException("URI Invalido:" + uri);
       }
       if (rows > 0) notifyChanges(uri);
       return rows;

       }

   }

