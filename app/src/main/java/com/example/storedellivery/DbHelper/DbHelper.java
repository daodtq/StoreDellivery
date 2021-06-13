package com.example.storedellivery.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.storedellivery.Model.Store;


public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "store.db";
    private static final String TABLE_NAME = "store";
    private static final String COLUMN1 = "StoreID";
    private static final String COLUMN2 = "StoreName";
    private static final String COLUMN3 = "StoreAddress";
    private static final String COLUMN4 = "StorePhone";
    private static final String COLUMN5 = "StoreLat";
    private static final String COLUMN6 = "StoreLong";
    private static final String COLUMN7 = "StoreImage";
    private static final String COLUMN8 = "Token";
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME+"("+COLUMN1+" INTEGER,"+ COLUMN2+" TEXT,"+COLUMN3+" TEXT,"+COLUMN4+" INTEGER,"+COLUMN5+" DOUBLE,"+COLUMN6+" DOUBLE,"+COLUMN7+" TEXT,"+COLUMN8+" TEXT"+")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private String sql = "SELECT * FROM " + TABLE_NAME;
    private String delete = "DELETE FROM " + TABLE_NAME;


    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addStore(Store store){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1,store.getStoreID());
        values.put(COLUMN2,store.getStoreName());
        values.put(COLUMN3,store.getStoreAddress());
        values.put(COLUMN4,store.getStorePhone());
        values.put(COLUMN5, store.getStoreLat());
        values.put(COLUMN6,store.getStoreLong());
        values.put(COLUMN7,store.getStoreImage());
        values.put(COLUMN8,store.getToken());
        database.insert(TABLE_NAME,null,values);
        database.close();
    }

    public Store getStore(String...a){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(sql,a);
        Store store = new Store();
        while (c.moveToNext()){
            store.setStoreID(c.getInt(c.getColumnIndex(COLUMN1)));
            store.setStoreName(c.getString(c.getColumnIndex(COLUMN2)));
            store.setStoreAddress(c.getString(c.getColumnIndex(COLUMN3)));
            store.setStorePhone(c.getInt(c.getColumnIndex(COLUMN4)));
            store.setStoreLat(c.getDouble(c.getColumnIndex(COLUMN5)));
            store.setStoreLong(c.getDouble(c.getColumnIndex(COLUMN6)));
            store.setStoreImage(c.getString(c.getColumnIndex(COLUMN7)));
            store.setToken(c.getString(c.getColumnIndex(COLUMN8)));
        }
        return store;
    }

    public void delete(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(delete);
    }
}
