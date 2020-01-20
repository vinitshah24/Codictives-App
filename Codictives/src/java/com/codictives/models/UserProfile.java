package com.codictives.models;

import java.io.Serializable;
import java.util.ArrayList;
import com.codictives.models.UserConnection;

/**
 * JavaBean class to create UserProfile Object
 *
 * @author Vinit Shah
 */
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private ArrayList<UserConnection> connections;

    public UserProfile() {
        user = new User();
        connections = null;
    }

    public UserProfile(User user, ArrayList<UserConnection> connections) {
        this.user = user;
        this.connections = connections;
    }

    /**
     *
     * @return user Object
     */
    public User getUser() {
        return this.user;
    }

    /**
     *
     * @return UserConnections
     */
    public ArrayList<UserConnection> getUserConnection() {
        return connections;
    }

    /*
     * Adds a UserConnection for this connection / rsvp / made it to the user profile.
     * The profile should not allow multiple UserConnection for the same connection,
     * but should update appropriately if one already exists
     *
     * @param Connection
     * @param rsvp
     * @param madeIt
     */
    public void addConnection(String connectionID, String rsvp) {
        boolean isFound = false;

        //If empty, add new connection
        if (connections.isEmpty()) {
            connections.add(new UserConnection(connectionID, rsvp));
        } else {
            // Else If connection already exists, update that connection
            for (int i = 0; i < connections.size(); i++) {
                if (connections.get(i).getConnectionID().equals(connectionID)) {
                    connections.set(i, new UserConnection(connectionID, rsvp));
                    isFound = true;
                    break;
                }
            }
        }
        //If doesn't exists, add new connection
        if (isFound == false) {
            connections.add(new UserConnection(connectionID, rsvp));
        }
    }

    /*
     * Adds a UserConnection to the user profile. 
     * The profile should not allow multiple UserConnection for the same connection, 
     * but should update appropriately if one already exists.
     *
     * @param connection
     */
    public void addConnection(UserConnection uConnection) {
        //If empty, add new connection
        if (connections.isEmpty()) {
            connections.add(uConnection);
        }

        boolean isFound = false;
        for (int i = 0; i < connections.size(); i++) {
            //If connection already exists, update that connection
            if (connections.get(i).getConnectionID().equals(uConnection.getConnectionID())) {
                connections.set(i, uConnection);
                isFound = true;
                break;
            }
        }

        //If doesn't exists, add new connection
        if (isFound == false) {
            connections.add(uConnection);
        }
    }

    /**
     * Removes any UserConnection associated with the given Connection
     *
     * @param uConnection
     */
    public void removeConnection(UserConnection uConnection) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).getConnectionID().equals(uConnection.getConnectionID())) {
                connections.remove(i);
                break;
            }
        }
    }

    /**
     * Updates a UserConnection data (rsvp, made it)
     *
     * @param uConnection
     */
    public void updateConnection(UserConnection uConnection) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).getConnectionID().equals(uConnection.getConnectionID())) {
                connections.set(i, uConnection);
                break;
            }
        }
    }

    /**
     * Returns a List / Collection of UserConnection from the user profile
     *
     * @return connections
     */
    public ArrayList<UserConnection> getConnections() {
        return connections;
    }

    /**
     * Clears the entire profile contents
     *
     */
    public void emptyProfile() {
        connections.clear();
    }
}