package net.glm.motohelp;

import java.util.List;

/**
 * Created by Michael on 10/09/2017.
 */

public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String motorbikeKind;
    private String [] skills;
    private Integer id;

    public User(String firstName, String lastName, String phoneNumber, String email, String motorbikeKind, String[] skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.motorbikeKind = motorbikeKind;
        this.skills = skills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotorbikeKind() {
        return motorbikeKind;
    }

    public void setMotorbikeKind(String motorbikeKind) {
        this.motorbikeKind = motorbikeKind;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
