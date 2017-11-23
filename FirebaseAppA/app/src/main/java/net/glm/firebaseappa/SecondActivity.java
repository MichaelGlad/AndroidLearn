package net.glm.firebaseappa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    static final String MY_LOGS = "My_Logs";
    private EditText etEmail,etPassword;
    Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(SecondActivity.this,AcountActivity.class));

                }else {
                    Toast.makeText(SecondActivity.this, "The Login Not Success!!!", Toast.LENGTH_SHORT).show();
                }

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
       firebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onClick(View view) {
        Log.d(MY_LOGS,"In OnClick");
        signIn();

    }

    private void signIn(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, " Please fill the fields !!!", Toast.LENGTH_SHORT).show();

        }else {

            Log.d(MY_LOGS,"In signIn Start");
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    Log.d(MY_LOGS,"In signIn");
                    if (!task.isSuccessful()) {
                        Toast.makeText(SecondActivity.this, " Sign In Problem", Toast.LENGTH_SHORT).show();
                    } else{
                        Log.d(MY_LOGS,"Sign In success");
                    }
                }
            });
        }

    }
}
