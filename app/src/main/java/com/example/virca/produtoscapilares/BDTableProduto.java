package com.example.virca.produtoscapilares;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.Date;

public class BDTableProduto implements BaseColumns {
    public static final String FIELD_ID_CATEGORY = "idcategory";
    public static final String FIELD_QUANTIDADE = "QUANTIDADE";
    public static final String FIELD_NOME = "NOME";
    public static final String TABLE_NAME = "PRODUTOS";

    public  static final String [] All_COLUMNS = new String[]{_ID,FIELD_ID_CATEGORY,
            FIELD_NOME,FIELD_QUANTIDADE};
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
  public   static ContentValues getContentValues(ProdutosCapilares produtosCapilares) {
      ContentValues values = new ContentValues();


      values.put(_ID, produtosCapilares.getId());
      values.put(FIELD_NOME, produtosCapilares.getNome());
      values.put(FIELD_QUANTIDADE, produtosCapilares.getQuantidade());
      values.put(FIELD_ID_CATEGORY, produtosCapilares.getQuantidade());

      return values;
  }


  public static ProdutosCapilares getCurrentProdutosCapilaresFromCursor(Cursor cursor){

        final int posId = cursor.getColumnIndex(_ID);
        final int posNome = cursor.getColumnIndex(FIELD_NOME);
        final int posQuantidade = cursor.getColumnIndex(FIELD_QUANTIDADE);
        final  int posIdCategory = cursor.getColumnIndex(FIELD_ID_CATEGORY);

        ProdutosCapilares produtosCapilaresl = new ProdutosCapilares();

        produtosCapilaresl.setId(cursor.getInt(posId));
        produtosCapilaresl.setNome(cursor.getString(posNome));
        produtosCapilaresl.setIdCategory(cursor.getInt(posIdCategory));
        produtosCapilaresl.setQuantidade(cursor.getInt(posQuantidade));



        return  produtosCapilaresl;

    }

    public long insert (ContentValues values) {
        return db.insert(TABLE_NAME, null, values);

    }
    public int update(ContentValues values,String whereClause,String[] whereArgs){
        return db.update(TABLE_NAME,values,whereClause,whereArgs);
    }

    public int delete(String whereClause,String[] whereArgs){
        return  db.delete(TABLE_NAME,whereClause,whereArgs);
    }

    public Cursor query(String[] columns,String selection,String[] selectionArgs,
                        String groupBy,String having,String orderBy){
        return db.query(TABLE_NAME,columns,selection,selectionArgs,
                groupBy,having,orderBy);
    }
}
