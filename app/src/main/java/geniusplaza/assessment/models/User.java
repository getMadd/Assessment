package geniusplaza.assessment.models;

/**
 * Created by jamarkushodge on 4/2/19.
 */

public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String avatar;

    public User(){

    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setFirst_name(String first_name ) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(int id) {
        this.id = id;
    }
}
