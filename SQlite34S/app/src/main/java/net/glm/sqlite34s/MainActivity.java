package net.glm.sqlite34s;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnDelete,btnUpdate;
    Button btnGroup, btnSum;
    EditText etName,etMakat,etPrice,etGroup;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnSum.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etGroup = (EditText) findViewById(R.id.etGroup);
        etMakat = (EditText) findViewById(R.id.etMakat);
        etPrice = (EditText) findViewById(R.id.etPrice);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        ContentValues contentValues = new ContentValues();

        String productName = etName.getText().toString();
        String group = etGroup.getText().toString();
        String makat = etMakat.getText().toString();
        String priceString = etPrice.getText().toString();
        Log.d(LOG_TAG,"Before getWriteble");
        SQLiteDatabase myDB = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG,"after getWriteble");
        float price;

        Cursor cursor=null;
        String[] columns =null;

        switch (v.getId()){
            case R.id.btnAdd:

                if (productName.isEmpty()){
                    Toast.makeText(this, "Please enter name of Product", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (makat.isEmpty()){
                    Toast.makeText(this, "Please enter Catalog number of Product", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (priceString.isEmpty()){
                    Toast.makeText(this, "Please enter price of Product", Toast.LENGTH_SHORT).show();
                    return;
                }
                price = Float.parseFloat(priceString);



                Log.d(LOG_TAG," ------ Add row to DB -----");
                contentValues.put(dbHelper.PRODUCT_NAME,productName);
                contentValues.put(dbHelper.GROUP,group);
                contentValues.put(dbHelper.MAKAT,makat);
                contentValues.put(dbHelper.PRICE,price);
                long rowID = myDB.insert(dbHelper.PRODUCT_TABLE,null,contentValues);
                Log.d(LOG_TAG,"number of inserted row ID - " + rowID);
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG," ----- Read from DB ------");

                cursor = myDB.query(dbHelper.PRODUCT_TABLE,null,null,null,null,null,null);


                if (cursor.moveToFirst()){
                    int idColumnIndex = cursor.getColumnIndex(dbHelper.ID);
                    int nameColumnIndex = cursor.getColumnIndex(dbHelper.PRODUCT_NAME);
                    int groupColumnIndex = cursor.getColumnIndex(dbHelper.GROUP);
                    int makatColumnIndex = cursor.getColumnIndex(dbHelper.MAKAT);
                    int priceColumnIndex = cursor.getColumnIndex(dbHelper.PRICE);
                    do{
                        Log.d(LOG_TAG,
                                "ID - " + cursor.getInt(idColumnIndex) +
                                "  Product Name - " + cursor.getString(nameColumnIndex) +
                                "  Group - " + cursor.getString(groupColumnIndex) +
                                "  Catalog Number - " + cursor.getString(makatColumnIndex)+
                                "  Price - " + cursor.getFloat(priceColumnIndex)  );

                    }while (cursor.moveToNext());

                }else
                    Log.d(LOG_TAG," ----- 0 Rows ");


                break;
            case R.id.btnUpdate:
                if (makat.isEmpty()){
                    Toast.makeText(this, "Please enter Catalog number of Product for UpDate", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (!productName.isEmpty()){
                    contentValues.put(dbHelper.PRODUCT_NAME,productName);
                }

                if (!priceString.isEmpty()){
                    price = Float.parseFloat(priceString);
                    contentValues.put(dbHelper.PRICE,price);
                }

                if (!group.isEmpty()){
                    contentValues.put(dbHelper.GROUP,group);
                }


                int updCount = myDB.update(dbHelper.PRODUCT_TABLE,contentValues, dbHelper.MAKAT +"=?",new String[] {makat} );
                Log.d(LOG_TAG," ----- UpDate the " + makat + " in DB --count " + updCount );
                break;
            case R.id.btnDelete:
                if (productName.isEmpty()){
                    Toast.makeText(this, "Please enter name of Product", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(LOG_TAG," ----- Delete from DB - " + productName );
                myDB.delete(dbHelper.PRODUCT_TABLE,dbHelper.PRODUCT_NAME + "=?",new String[]{productName});
                break;
            case R.id.btnGroup:
                Log.d(LOG_TAG, "--- Sum of Prices of Group ---");
                columns = new String[] { dbHelper.GROUP, "sum(" + dbHelper.PRICE +") as " + dbHelper.PRICE };
                cursor = myDB.query(dbHelper.PRODUCT_TABLE, columns, null, null, dbHelper.GROUP, null, null);
                if (cursor.moveToFirst()) {
                    String str;
                    do {
                        str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        Log.d(LOG_TAG, str);

                    } while (cursor.moveToNext());
                }
                break;
            case R.id.btnSum:
                Log.d(LOG_TAG, "--- Sum of Prices of Group ---");
                columns = new String[] { dbHelper.GROUP, "sum(" + dbHelper.PRICE +") as " + dbHelper.PRICE };
                String having = "sum(" + dbHelper.PRICE + ") > " + priceString;
                cursor = myDB.query(dbHelper.PRODUCT_TABLE, columns, null, null, dbHelper.GROUP, having, null);
                if (cursor.moveToFirst()) {
                    String str;
                    do {
                        str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        Log.d(LOG_TAG, str);

                    } while (cursor.moveToNext());
                }
                break;
        }
        dbHelper.close();




    }
}
