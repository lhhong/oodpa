package group1.restaurant;

import java.io.Serializable;

/**
 * Created by signapoop on 4/11/16.
 */
public class Staff implements Serializable{
    private String name;
    private Gender gender;
    private int employeeID;
    private String jobTitle;

    public Staff(String n, Gender g, int e, String j){
        setName(n);
        setGender(g);
        setEmployeeID(e);
        setJobTitle(j);
    }

    public String toString(){
        return name+" "+gender+" "+employeeID+" "+jobTitle;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}

