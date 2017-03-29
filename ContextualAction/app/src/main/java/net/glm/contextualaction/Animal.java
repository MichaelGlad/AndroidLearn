package net.glm.contextualaction;

/**
 * Created by Michael on 29/03/2017.
 */


public class Animal {

    private Integer image;
    private String name;
    private String mail;

    public Animal(Integer image, String name, String mail) {
        this.image = image;
        this.name = name;
        this.mail = mail;
    }


    public int getImage() {

        return image;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }


}


