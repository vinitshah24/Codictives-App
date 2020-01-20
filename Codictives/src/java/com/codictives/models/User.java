package com.codictives.models;

import java.util.UUID;
import java.io.Serializable;

/**
 * JavaBean class to create User Object
 *
 * @author Vinit Shah
 */
public class User implements Serializable {

    private int userID;
    private String password;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String zip;
    private String country;

    /**
     * Default Constructor
     */
    public User() {
        this.userID = 0;
        this.password = "";
        this.userName = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.address = "";
        this.city = "";
        this.zip = "";
        this.country = "";
    }

    /**
     * User Constructor without userID parameter
     *
     * @param password
     * @param userName
     * @param firstName
     * @param lastName
     * @param email
     * @param address
     * @param city
     * @param zip
     * @param country
     */
    public User(String userName, String password, String firstName,
            String lastName, String email, String address,
            String city, String zip, String country) {
        this.userID = 0;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    /**
     * User Constructor with all User object parameters
     *
     * @param userID
     * @param password
     * @param userName
     * @param firstName
     * @param lastName
     * @param email
     * @param address
     * @param city
     * @param zip
     * @param country
     */
    public User(int userID, String userName, String password,
            String firstName, String lastName, String email,
            String address, String city, String zip, String country) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    /**
     *
     * @param userID
     */
    public void setUserId(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return Random string for unique id
     */
    private String generateRandomId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
