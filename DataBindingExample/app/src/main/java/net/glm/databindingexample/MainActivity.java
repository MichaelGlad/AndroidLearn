package net.glm.databindingexample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.glm.databindingexample.databinding.ActivityMainBinding;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;
    UserData userDataObservable;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userDataObservable = new UserData();
        user = new User(getResources().getString(R.string.hint_first_name_tv),getResources().getString(R.string.hint_last_name_tv));
        binding.setUser(user);
//        userDataObservable.addObserver(this);


    }

    public void onClickDone(View view) {
        User userFromEditText = new User(null,null);
        String firstNameFromEditText = binding.etFirstName.getText().toString().trim();
        String lastNameFromEditText = binding.etLastName.getText().toString().trim();


        if (firstNameFromEditText != null && !firstNameFromEditText.isEmpty()) {
            userFromEditText.setFirstName(firstNameFromEditText);
        }
        if (lastNameFromEditText != null && !lastNameFromEditText.isEmpty()) {
            userFromEditText .setLastName(lastNameFromEditText);
        }
        user.setFirstName(userFromEditText.getFirstName());
        user.setLastName(userFromEditText.getLastName());
        userDataObservable.getNewUserData(user);

    }

//    @Override
//    public void update(Observable observable, Object arg) {
//        if (observable instanceof UserData) {
//            if (arg != null && arg instanceof User) {
//                User user = (User) arg;
//                binding.setVariable(BR.user, user);
//            }
//        }
//
//    }
}
