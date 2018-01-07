package net.glm.instagramtest.dataModel;

/**
 * Created by Michael on 22/12/2017.
 */

public class Data {
    private Image images;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImages() {

        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }
}
