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


    public static String [] ALL_COLUMNS = new  String[]{_ID,FIELD_ID_PRODUTO,FIELD_DATA,FIELD_QUANTIDADE};


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


    public static ContentValues getContentValues (Entradas entradas){
        ContentValues values = new ContentValues();


        values.put(FIELD_DATA,entradas.getData());
        values.put(FIELD_ID_PRODUTO,entradas.getId_produto());
        values.put(FIELD_QUANTIDADE,entradas.getQuantidade());


        return values;
    }
public static Entradas getCurrentEntradasFromCursor(Cursor cursor){

      final int posId = cursor.getColumnIndex(_ID);
      final int posData = cursor.getColumnIndex(FIELD_DATA);
      final int posIdProduto = cursor.getColumnIndex(FIELD_ID_PRODUTO);
      final int posquantidade = cursor.getColumnIndex(FIELD_QUANTIDADE);

      Entradas entradas = new Entradas ();

      entradas.setId(cursor.getInt(posId));
      entradas.setData(cursor.getString(posData));
      entradas.setId_produto(cursor.getInt(posIdProduto));
      entradas.setQuantidade(cursor.getInt(posquantidade));

      return entradas;
}


public long insert (ContentValues values){
        return db.insert(TABLE_NAME,null,values);
}

 public int update(ContentValues values,String whereClause,String[] whereArgs){
        return db.update(TABLE_NAME,values,whereClause,whereArgs);
 }
 public int delete (String whereClause, String[] whereArgs){
        return db.delete(TABLE_NAME,whereClause,whereArgs);
 }

   public Cursor query (String [] colums,String selection,String [] selectionArgs,String groupBy,String having ,String orderBy){
        return  db.query(TABLE_NAME,colums,selection,selectionArgs,
                groupBy,having,orderBy);

   }


}
