package com.codictives.models;

import java.io.Serializable;

/**
 * JavaBean class to create Connection Object
 *
 * @author Vinit Shah
 */
public class ConnectionWithRsvp implements Serializable {

    private static final long serialVersionUID = 1L;

    private int connectionId;
    private String name;
    private String topic;
    private String details;
    private String location;
    private String date;
    private String time;
    private int ownerId;
    private int userId;
    private String rsvp;

    /**
     * Default Constructor
     */
    public ConnectionWithRsvp() {
        this.connectionId = 0;
        this.name = "";
        this.topic = "";
        this.details = "";
        this.location = "";
        this.date = "";
        this.time = "";
        this.ownerId = 0;
        this.userId = 0;
        this.rsvp = "";
    }

    /**
     * Constructor without Connection ID parameter
     *
     * @param name
     * @param topic
     * @param details
     * @param location
     * @param date
     * @param time
     */
    /**
     * Constructor with all Connection object parameters
     *
     * @param connectionId
     * @param name
     * @param topic
     * @param details
     * @param location
     * @param date
     * @param time
     * @param ownerId
     * @param userId
     * @param rsvp
     */
    public ConnectionWithRsvp(int connectionId, String name, String topic,
            String details, String location, String date, String time,
            int ownerId, int userId, String rsvp) {
        this.connectionId = connectionId;
        this.name = name;
        this.topic = topic;
        this.details = details;
        this.location = location;
        this.date = date;
        this.time = time;
        this.ownerId = ownerId;
        this.userId = userId;
        this.rsvp = rsvp;
    }

    /**
     *
     * @param connectionId
     */
    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @param ownerId
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @param rsvp
     */
    public void setRsvp(String rsvp) {
        this.rsvp = rsvp;
    }

    /**
     *
     * @return Connection id
     */
    public int getConnectionId() {
        return connectionId;
    }

    /**
     *
     * @return Connection name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Connection topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     *
     * @return Connection details
     */
    public String getDetails() {
        return details;
    }

    /**
     *
     * @return Connection location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @return Connection date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @return Connection time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @return ownerId
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     *
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return RSVP
     */
    public String getRsvp() {
        return rsvp;
    }
}
