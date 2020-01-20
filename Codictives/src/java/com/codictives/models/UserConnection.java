package com.codictives.models;

import java.io.Serializable;

/**
 * JavaBean class to create User Connection Object
 *
 * @author Vinit Shah
 */
public class UserConnection implements Serializable {

    private static final long serialVersionUID = 1L;

    private String connectionID;
    private String rsvp;

    /**
     * Default User Connection Constructor
     */
    public UserConnection() {
    }

    /**
     * User Connection Constructor with all User object parameters
     *
     * @param connectionID
     * @param rsvp
     */
    public UserConnection(String connectionID, String rsvp) {
        this.connectionID = connectionID;
        this.rsvp = rsvp;
    }

    /**
     *
     * @param connectionID
     */
    public void setConnection(String connectionID) {
        this.connectionID = connectionID;
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
     * @return connectionID
     */
    public String getConnectionID() {
        return connectionID;
    }

    /**
     *
     * @return RSVP
     */
    public String getRsvp() {
        return rsvp;
    }
}
