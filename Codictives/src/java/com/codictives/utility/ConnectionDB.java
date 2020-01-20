package com.codictives.utility;

import com.codictives.models.Connection;
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
 * ConnectionDB class for creating and managing list of Connections objects
 *
 * @author Vinit Shah
 */
public class ConnectionDB {

    /**
     * Getting all the connections object
     *
     * @return Connections list
     */
    public static ArrayList<Connection> getConnections() {
        ArrayList<Connection> connections = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM connections ORDER BY topic";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Connection con = null;
            while (rs.next()) {
                con = new Connection();
                try {
                    if (ESAPI.validator().isValidInteger("connectionid",
                            Integer.toString(rs.getInt("connectionid")), 0, 100000, false)) {
                        con.setConnectionId(rs.getInt("connectionid"));
                    } else {
                        con.setConnectionId(0);
                    }
                    con.setName(
                            ESAPI.validator().getValidInput("ConName",
                                    rs.getString("name"), "SafeString", 200, false)
                    );
                    con.setTopic(
                            ESAPI.validator().getValidInput("ConTopic",
                                    rs.getString("topic"), "SafeString", 200, false)
                    );
                    con.setDetails(rs.getString("details")
                            .trim().replaceAll("^ +| +$|( )+", "$1"));
                    con.setLocation(
                            ESAPI.validator().getValidInput("ConDetails",
                                    rs.getString("location"), "SafeString", 200, false)
                    );
                    con.setDate(
                            ESAPI.validator().getValidInput("ConDetails",
                                    rs.getString("date"), "FileName", 200, false)
                    );

                    if (ESAPI.validator().isValidInteger("ownerid",
                            Integer.toString(rs.getInt("ownerid")), 0, 100000, false)) {
                        con.setOwnerId(rs.getInt("ownerid"));
                    } else {
                        con.setOwnerId(0);
                    }
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ConnectionDB.class.getName()).
                            log(Level.SEVERE, null, ex);
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

    /**
     * Adding a new connection to the list
     *
     * @param con
     */
    public static int addConnection(Connection con) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO connections (name, topic, details, "
                + "location, date, time, ownerid) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, con.getName());
            ps.setString(2, con.getTopic());
            ps.setString(3, con.getDetails());
            ps.setString(4, con.getLocation());
            ps.setString(5, con.getDate());
            ps.setString(6, con.getTime());
            ps.setInt(7, con.getOwnerId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    /**
     * Getting the connection object based on the Connection ID
     *
     * @param connectionName
     * @return Connection object
     */
    public static Connection getConnection(String connectionName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM connections WHERE name = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, connectionName);
            rs = ps.executeQuery();
            Connection con = null;
            if (rs.next()) {
                con = new Connection();
                con.setConnectionId(rs.getInt("connectionid"));
                try {
                    con.setName(
                            ESAPI.validator().getValidInput("ConName",
                                    rs.getString("name"), "SafeString", 200, false)
                    );
                    con.setTopic(
                            ESAPI.validator().getValidInput("ConTopic",
                                    rs.getString("topic"), "SafeString", 200, false)
                    );
                    con.setDetails(rs.getString("details")
                            .trim().replaceAll("^ +| +$|( )+", "$1"));
                    con.setLocation(
                            ESAPI.validator().getValidInput("ConLocation",
                                    rs.getString("location"), "SafeString", 200, false)
                    );
                    con.setDate(
                            ESAPI.validator().getValidInput("ConDate",
                                    rs.getString("date"), "FileName", 200, false)
                    );
                    con.setTime(
                            ESAPI.validator().getValidInput("ConTime",
                                    rs.getString("time"), "Time", 200, false)
                    );
                    if (ESAPI.validator().isValidInteger("ownerid",
                            Integer.toString(rs.getInt("ownerid")), 0, 100000, false)) {
                        con.setOwnerId(rs.getInt("ownerid"));
                    } else {
                        con.setOwnerId(0);
                    }
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return con;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int removeConnection(Connection con) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM connections WHERE name = ? AND ownerid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, con.getName());
            ps.setInt(2, con.getOwnerId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int getConnectionId(String connectionName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String query = "SELECT connectionid FROM connections WHERE name = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, connectionName);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("connectionid");
            }
            return id;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Connection> getOwnerConnections(int ownerid) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Connection> connections = new ArrayList<Connection>();
        Connection con = null;
        String query = "SELECT * FROM connections WHERE ownerid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ownerid);
            rs = ps.executeQuery();
            while (rs.next()) {
                con = new Connection();
                if (ESAPI.validator().isValidInteger("connectionid",
                        Integer.toString(rs.getInt("connectionid")), 0, 100000, false)) {
                    con.setConnectionId(rs.getInt("connectionid"));
                } else {
                    con.setConnectionId(0);
                }
                try {
                    con.setName(
                            ESAPI.validator().getValidInput("ConName",
                                    rs.getString("name"), "SafeString", 200, false)
                    );
                    con.setTopic(
                            ESAPI.validator().getValidInput("ConTopic",
                                    rs.getString("topic"), "SafeString", 200, false));
                    con.setDetails(rs.getString("details").trim()
                            .trim().replaceAll("^ +| +$|( )+", "$1"));
                    con.setLocation(
                            ESAPI.validator().getValidInput("ConLocation",
                                    rs.getString("location"), "SafeString", 200, false));
                    con.setDate(
                            ESAPI.validator().getValidInput("ConDate",
                                    rs.getString("date"), "FileName", 200, false)
                    );
                    con.setOwnerId(rs.getInt("ownerid"));
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
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

    public static int updateOwnerConnection(Connection con) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            ps = connection.prepareStatement("UPDATE connections "
                    + "SET name = ?, topic = ?, details = ?, location = ?, "
                    + "date = ?, time = ? WHERE connectionid = ?");
            ps.setString(1, con.getName());
            ps.setString(2, con.getTopic());
            ps.setString(3, con.getDetails());
            ps.setString(4, con.getLocation());
            ps.setString(5, con.getDate());
            ps.setString(6, con.getTime());
            ps.setInt(7, con.getConnectionId());
            result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int removeOwnerConnection(int connectionid, int ownerid) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            ps = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            result = ps.executeUpdate();

            ps = connection.prepareStatement("DELETE FROM connections "
                    + "WHERE connectionid = ? AND ownerid = ?");
            ps.setInt(1, connectionid);
            ps.setInt(2, ownerid);
            result = ps.executeUpdate();

            ps = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            result = ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
