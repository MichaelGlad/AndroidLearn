package net.glm.sqlite34s;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Michael on 09/07/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    final String ID = "id";
    final String PRODUCT_TABLE = "productTable";
    final String LOG_TAG = "myLogs";
    final String PRODUCT_NAME = "name";
    final String MAKAT = "makat";
    final String PRICE = "price";

    public DBHelper(Context context) {
        super(context, "myDB",null, 1);
        Log.d(LOG_TAG,"In DBHelper Constractor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG,"In DBHelper onCreate");
        db.execSQL("create table " + PRODUCT_TABLE + " ("
                   + ID + " integer primary key autoincrement, "
                   + PRODUCT_NAME + " text, "
                   + MAKAT + " text, "
                   + PRICE + " real" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
