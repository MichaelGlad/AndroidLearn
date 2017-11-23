package net.glm.firebaseauthlearn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Michael on 06/11/2017.
 */

public class BaseActivity extends AppCompatActivity{

    public ProgressDialog myProgressDialog;

    public void showProgressDialog(){
        if (myProgressDialog == null){
            myProgressDialog =new ProgressDialog(this);
            myProgressDialog.setMessage(getString(R.string.loading));
            myProgressDialog.setIndeterminate(true);
        }
        myProgressDialog.show();
    }

    public void hideProgressDialog(){
        if((myProgressDialog != null) && myProgressDialog.isShowing()){
            myProgressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
