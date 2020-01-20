/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codictives.utility;

import com.codictives.models.ConnectionWithRsvp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author shahv
 */
public class UserConnectionDB {

    public static ArrayList<ConnectionWithRsvp> getConnections(int userID) {
        ArrayList<ConnectionWithRsvp> connections = new ArrayList<ConnectionWithRsvp>();
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT connections.*, userconnections.userid, userconnections.rsvp "
                + "FROM userconnections "
                + "INNER JOIN users "
                + "ON userconnections.userid = users.userid "
                + "INNER JOIN connections "
                + "ON userconnections.connectionid = connections.connectionid "
                + "WHERE userconnections.userid = ? "
                + "ORDER BY connections.topic";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            ConnectionWithRsvp con = null;
            while (rs.next()) {
                con = new ConnectionWithRsvp();
                if (ESAPI.validator().isValidInteger("connectionid",
                        Integer.toString(rs.getInt("connectionid")), 0, 100000, false)) {
                    con.setConnectionId(rs.getInt("connectionid"));
                } else {
                    con.setConnectionId(0);
                }
                try {
                    con.setName(
                            ESAPI.validator().getValidInput("name",
                                    rs.getString("name"), "SafeString", 200, false)
                    );
                    con.setTopic(
                            ESAPI.validator().getValidInput("topic",
                                    rs.getString("topic"), "SafeString", 200, false));
                    con.setDetails(rs.getString("details")
                            .trim().replaceAll("^ +| +$|( )+", "$1"));
                    con.setLocation(
                            ESAPI.validator().getValidInput("location",
                                    rs.getString("location"), "SafeString", 200, false)
                    );
                    con.setDate(
                            ESAPI.validator().getValidInput("date",
                                    rs.getString("date"), "FileName", 200, false));
                    con.setTime(
                            ESAPI.validator().getValidInput("time",
                                    rs.getString("time"), "Time", 200, false));
                    if (ESAPI.validator().isValidInteger("ownerid",
                            Integer.toString(rs.getInt("ownerid")), 0, 100000, false)) {
                        con.setOwnerId(rs.getInt("ownerid"));
                    } else {
                        con.setOwnerId(0);
                    }
                    if (ESAPI.validator().isValidInteger("userid",
                            Integer.toString(rs.getInt("userid")), 0, 100000, false)) {
                        con.setUserId(rs.getInt("userid"));
                    } else {
                        con.setOwnerId(0);
                    }
                    con.setRsvp(
                            ESAPI.validator().getValidInput("rsvp",
                                    rs.getString("rsvp"), "SafeString", 200, false)
                    );
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(UserConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                connections.add(con);
            }
            return connections;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int removeConnection(int userID, int connectionID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM userconnections WHERE userid=? AND connectionid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, connectionID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int addConnection(int userID, int connectionID, String rsvp) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO userconnections (userid, connectionid, rsvp) "
                + "VALUES (?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, connectionID);
            ps.setString(3, rsvp);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int updateConnection(int userID, int connectionID, String rsvp) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE userconnections SET rsvp = ? "
                + "WHERE connectionid = ? AND userid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, rsvp);
            ps.setInt(2, connectionID);
            ps.setInt(3, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int validateEntry(int userID, int connectionID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int output = 0;
        String query = "SELECT COUNT(*) > 0 AS output "
                + "FROM userconnections WHERE userid = ? AND connectionid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, connectionID);
            rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getInt("output");
            }
            return output;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
