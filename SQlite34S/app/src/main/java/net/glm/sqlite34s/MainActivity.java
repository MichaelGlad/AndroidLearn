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
    final String PRODUCT_NAME = "name";
    final String MAKAT = "makat";
    final String PRICE = "price";

    Button btnAdd, btnRead, btnDelete;
    EditText etName,etMakat,etPrice;

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

        etName = (EditText) findViewById(R.id.etName);
        etMakat = (EditText) findViewById(R.id.etMakat);
        etPrice = (EditText) findViewById(R.id.etPrice);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        ContentValues contentValues = new ContentValues();

        String productName = etName.getText().toString();
        String makat = etMakat.getText().toString();
        String priceString = etPrice.getText().toString();

        SQLiteDatabase myDB = dbHelper.getWritableDatabase();

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
                float price = Float.parseFloat(priceString);



                Log.d(LOG_TAG," ------ Add row to DB -----");
                contentValues.put(PRODUCT_NAME,productName);
                contentValues.put(MAKAT,makat);
                contentValues.put(PRICE,price);
                long rowID = myDB.insert(dbHelper.PRODUCT_TABLE,null,contentValues);
                Log.d(LOG_TAG,"number of inserted row ID - " + rowID);
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG," ----- Read from DB ------");

                Cursor cursor = myDB.query(dbHelper.PRODUCT_TABLE,null,null,null,null,null,null);


                if (cursor.moveToFirst()){
                    int idColumnIndex = cursor.getColumnIndex(dbHelper.ID);
                    int nameColumnIndex = cursor.getColumnIndex(dbHelper.PRODUCT_NAME);
                    int makatColumnIndex = cursor.getColumnIndex(dbHelper.MAKAT);
                    int priceColumnIndex = cursor.getColumnIndex(dbHelper.PRICE);
                    do{
                        Log.d(LOG_TAG,
                                "ID - " + cursor.getInt(idColumnIndex) + "  idColumn - " + idColumnIndex +
                                "  Product Name - " + cursor.getString(nameColumnIndex) + "  nameColumn - " + nameColumnIndex +
                                "  Catalog Number - " + cursor.getString(makatColumnIndex) + "  makatColumn - " + makatColumnIndex +
                                "  Price - " + cursor.getFloat(priceColumnIndex) + "  priceColumn - " + priceColumnIndex );

                    }while (cursor.moveToNext());

                }else
                    Log.d(LOG_TAG," ----- 0 Rows ");


                break;
            case R.id.btnDelete:
                if (productName.isEmpty()){
                    Toast.makeText(this, "Please enter name of Product", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(LOG_TAG," ----- Delete from DB - " + productName );
                myDB.delete(dbHelper.PRODUCT_TABLE,PRODUCT_NAME + "=?",new String[]{productName});
                break;
        }
        dbHelper.close();




    }
}
