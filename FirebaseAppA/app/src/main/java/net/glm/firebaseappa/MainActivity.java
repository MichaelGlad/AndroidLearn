package net.glm.firebaseappa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button btnAddData,btnAuth;
    private EditText etUserName;
    private TextView tvDatabseChange;
    private ListView listViewUsers;

    private Firebase myReferenceDataBase,myUserChildReferenceDataBase,myNameChildReferenceDataBase;

    private ArrayList <String> myUsersName = new ArrayList<>();

    int counter =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        myReferenceDataBase = new Firebase("https://fir-appa-b6006.firebaseio.com/");
        myUserChildReferenceDataBase = myReferenceDataBase.child("User");

        btnAddData = (Button) findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener( this);
        btnAuth = (Button) findViewById(R.id.btnAuth);
        btnAuth.setOnClickListener(this);
        etUserName = (EditText) findViewById(R.id.etUserName);
        tvDatabseChange = (TextView) findViewById(R.id.tvDatabaseChange);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myUsersName);
        listViewUsers.setAdapter(myArrayAdapter);

        myUserChildReferenceDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                myUsersName.add(value);
                myArrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_add_data){
            myNameChildReferenceDataBase = myUserChildReferenceDataBase.child("Name" + counter++);
            myNameChildReferenceDataBase.setValue(etUserName.getText().toString());

        }

        if (view.getId() == R.id.btnAuth){
            Intent intent = new Intent(this,SecondActivity.class);
            MainActivity.this.startActivity(intent);

        }
    }
}
