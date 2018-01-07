package net.glm.instagramtest.listener;

/**
 * Created by Michael on 22/12/2017.
 */

public interface AuthenticationListener {

    void onCodeReceived (String authToken);
}
