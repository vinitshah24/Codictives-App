package com.codictives.models;

/**
 *
 * @author shahv
 */
public class Feedback {

    private String firstName;
    private String lastName;
    private String topic;
    private String message;

    public Feedback(){
        
    }
    
    /**
     * 
     * @param firstName
     * @param lastName
     * @param topic
     * @param message 
     */
    public Feedback(String firstName, String lastName, String topic, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.topic = topic;
        this.message = message;
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
     * @param topic 
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 
     * @return message
     */
    public String getMessage() {
        return message;
    }

}
