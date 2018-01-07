package glm.net;

/**
 * Created by Michael on 17/09/2017.
 */
public class User {


    private String fullName;
    private String email;
    private int age;
    private Boolean isBringFuel;
    private Integer id;

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

    public Boolean getIsBringFuel() {
        return isBringFuel;
    }

    public void setBringFuel(Boolean bringFuel) {
        isBringFuel = bringFuel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
