package net.glm.firebaseauthlearn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailAuthActivity extends BaseActivity implements View.OnClickListener {



    TextView tvStatus;
    TextView tvUserId;
    EditText etEmail;
    EditText etPassword;
    LinearLayout llPasswordButtons;
    LinearLayout llSignedInButtons;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_auth);

        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_sign_out).setOnClickListener(this);
        findViewById(R.id.btn_create_account).setOnClickListener(this);
        findViewById(R.id.btn_verify_email).setOnClickListener(this);

        tvStatus = (TextView) findViewById(R.id.tv_status);
        tvUserId = (TextView) findViewById(R.id.tv_user_id);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        llPasswordButtons = (LinearLayout) findViewById(R.id.ll_password_buttons);
        llSignedInButtons = (LinearLayout) findViewById(R.id.ll_signed_in_buttons);

        mAuth = FirebaseAuth.getInstance();

    }

    private void updateUI (FirebaseUser user){
        hideProgressDialog();
        if (user !=null){

        }else{
            llPasswordButtons.setVisibility(View.VISIBLE);
            etPassword.setVisibility(View.VISIBLE);
            llSignedInButtons.setVisibility(View.GONE);

        }

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();



    }
}
