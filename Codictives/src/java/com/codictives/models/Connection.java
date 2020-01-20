package com.codictives.models;

import java.io.Serializable;

/**
 * JavaBean class to create Connection Object
 *
 * @author Vinit Shah
 */
public class Connection implements Serializable {

    private int connectionId;
    private String name;
    private String topic;
    private String details;
    private String location;
    private String date;
    private String time;
    private int ownerId;

    /**
     * Default Constructor
     */
    public Connection() {
        this.connectionId = 0;
        this.name = "";
        this.topic = "";
        this.details = "";
        this.location = "";
        this.date = "";
        this.time = "";
        this.ownerId = 0;
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
     */
    public Connection(int connectionId, String name, String topic, String details,
            String location, String date, String time, int ownerId) {
        this.connectionId = connectionId;
        this.name = name;
        this.topic = topic;
        this.details = details;
        this.location = location;
        this.date = date;
        this.time = time;
        this.ownerId = ownerId;
    }

    public Connection(String name, String topic, String details,
            String location, String date, String time, int ownerId) {
        this.name = name;
        this.topic = topic;
        this.details = details;
        this.location = location;
        this.date = date;
        this.time = time;
        this.ownerId = ownerId;
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

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }
}
