package group1.restaurant;

import java.io.Serializable;

/**
 * Represents a Staff that works in the Restaurant
 * @author OOP Group 1
 * @version 1.0
 * @since 2016-11-8
 */

public class Staff implements Serializable{
    /**
     * The name of the staff
     */
    private String name;
    /**
     * The gender of the staff
     */
    private Gender gender;
    /**
     * The ID of the staff
     */
    private int employeeID;
    /**
     * The job title of the staff
     */
    private String jobTitle;


    /**
     * Creates a new staff database with all possible attributes
     * @param n The name of the staff
     * @param g The gender of the staff
     * @param e The Employee ID of the staff
     * @param j The Job title of the staff
     */
    public Staff(String n, Gender g, int e, String j){
        setName(n);
        setGender(g);
        setEmployeeID(e);
        setJobTitle(j);
    }

    /**
     * Returns the string containing all details of a staff
     * @return The string of all possible attributes of the staff
     */
    public String toString(){
        return name+" "+gender+" "+employeeID+" "+jobTitle;
    }

    /**
     * Gets the name of a current staff
     * @return The name of the current staff
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of a current staff
     * @param name The new name of the staff
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the current staff
     * @return The gender of the current staff
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Changes the gender of the current staff
     * @param gender The new gender of the staff
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the employee ID of a current staff
     * @return The employee ID of the staff
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Changes the Employee ID of a current staff
     * @param employeeID The new Employee ID of the staff
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets the job title of a current staff
     * @return The job title of a staff
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Changes the job title of a current staff
     * @param jobTitle The new job title of the staff
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}

