package com.example.virca.produtoscapilares;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Calendar;

public class DbTableSaida implements BaseColumns {
    public static final String TABLE_NAME = "saida";

    public static final String FIELD_DATA = "data";
    public static final String FIELD_QUANTIDADE = "quantidade";
    public static final String FIELD_ID_PRODUTO = "id_produto";

    public static final String [] ALL_COLUMNS = new String[]{_ID,FIELD_QUANTIDADE,FIELD_DATA,FIELD_ID_PRODUTO};

    private SQLiteDatabase db;


    public DbTableSaida(SQLiteDatabase db){
        this.db = db;
    }
    public void creat(){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_DATA + " TEXT NOT  NULL ," +
                FIELD_QUANTIDADE + " INTEGER," +
                      "FOREIGN KEY (" + FIELD_QUANTIDADE +") REFERENCES " +BDTableEntradas.TABLE_NAME +
                         "(" + BDTableEntradas.FIELD_QUANTIDADE +
                FIELD_ID_PRODUTO + " INTEGER ," +
                "FOREIGN KEY (" + FIELD_ID_PRODUTO + ") REFERENCES "
                + BDTableProduto.TABLE_NAME +
                "(" + BDTableProduto._ID + ")" +")"
        );
    }

    public static ContentValues getContentValues( Saida saida){
        ContentValues values = new ContentValues();

        values.put(_ID,saida.getId());
        values.put(FIELD_DATA,saida.getData());
        values.put(FIELD_ID_PRODUTO,saida.getId_produto());
        values.put(FIELD_QUANTIDADE,saida.getQuantidade());

        return values;
    }

    public static  Saida getCurrentSaidaFromCursor( Cursor cursor){
       final int posId = cursor.getColumnIndex(_ID);
       final int posQuantidade = cursor.getColumnIndex(FIELD_QUANTIDADE);
       final int posIdProduto = cursor.getColumnIndex(FIELD_ID_PRODUTO);
       final int posData = cursor.getColumnIndex(FIELD_DATA);

       Saida saida = new Saida();

       saida.setId(cursor.getInt(posId));
       saida.setData(cursor.getString(posData));
       saida.setId_produto(cursor.getInt(posIdProduto));
       saida.setQuantidade(cursor.getInt(posQuantidade));

       return saida;

    }
    public  long inserte(ContentValues values ){
        return db.insert(TABLE_NAME,null,values );
    }
    public int update(ContentValues values,String whereClause, String[] whereArgs){
        return db.update(TABLE_NAME,values,whereClause,whereArgs);
    }


    public int delete(String whereClause ,String[] whereArgs){
        return db.delete(TABLE_NAME,whereClause,whereArgs);
    }


    public Cursor query(String[] columns,String selection,String[] selectionArgs,
                        String groupBy,String having,String orderBy){
        return db.query(TABLE_NAME,columns,selection,selectionArgs,groupBy,having,orderBy);
    }


}
