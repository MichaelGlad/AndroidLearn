package net.glm.databindingexample;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.Observable;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class UserData {

    private User user;
    private int userId = 0;

    public UserData() {
        user = new User("", "");

    }


    public void getNewUserData(User newUser) {
        user = newUser;
        if ((user.getFirstName() == null) && (user.getLastName() == null)) {
            switch (userId) {
                case (0):
                    user.setFirstName("Mike");
                    user.setLastName("First");
                    userId++;
                    break;
                case (1):
                    user.setFirstName("Dan");
                    user.setLastName("Second");
                    userId++;
                    break;
                case (2):
                    user.setFirstName("Or");
                    user.setLastName("Third");
                    userId++;
                    break;
                case (3):
                    user.setFirstName("Yair");
                    user.setLastName("Fourth");
                    userId++;
                    break;
                default:
                    user.setFirstName("Mike");
                    user.setLastName("After Fourth");
                    userId++;


            }
        } else {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
        }


//        setChanged();
//        notifyObservers(user);

    }


}
