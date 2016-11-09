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
	    gender = g;
        employeeID = e;
        jobTitle = j;
        name = n;
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
     * Gets the gender of the current staff
     * @return The gender of the current staff
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the employee ID of a current staff
     * @return The employee ID of the staff
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the job title of a current staff
     * @return The job title of a staff
     */
    public String getJobTitle() {
        return jobTitle;
    }
}

