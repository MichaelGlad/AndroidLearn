package net.glm.retrofittest2;

/**
 * Created by Michael on 17/09/2017.
 */

public class User {


    private String fullName;
    private String email;
    private int age;
    private Boolean isBringFuel;
    private Boolean isRepairFlatTire;
    private Integer id;

    public User(){

    }

    public User(String fullName, String email, int age) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getBringFuel() {
        return isBringFuel;
    }

    public void setBringFuel(Boolean bringFuel) {
        isBringFuel = bringFuel;
    }

    public Boolean getRepairFlatTire() {
        return isRepairFlatTire;
    }

    public void setRepairFlatTire(Boolean repairFlatTire) {
        isRepairFlatTire = repairFlatTire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User is - "+ getFullName() + " Email - " + getEmail();
    }


    public String toStringID() {
        return "User is - "+ getFullName() + " Email - " + getEmail()+ " ID - " + getId();
    }
}

