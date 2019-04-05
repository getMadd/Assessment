package geniusplaza.assessment.models;

/**
 * Created by jamarkushodge on 4/4/19.
 */

public class Create {

    private String name;
    private String job;

    public Create(){

    }

    public Create(String name,String job){
        this.name = name;
        this.job = job;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getUserJob() {
        return job;
    }

    public void setUserJob(String userJob) {
        this.job = userJob;
    }
}
