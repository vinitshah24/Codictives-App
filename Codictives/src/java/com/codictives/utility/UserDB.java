package com.codictives.utility;

import com.codictives.models.User;
import java.sql.Connection;
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
 * @author Vinit Shah
 */
public class UserDB {

    /**
     *
     * @return User ArrayList
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM users";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                if (ESAPI.validator().isValidInteger("userid",
                        Integer.toString(rs.getInt("userid")), 0, 100000, false)) {
                    user.setUserId(rs.getInt("userid"));
                } else {
                    user.setUserId(0);
                }
                try {
                    user.setUserName(
                            ESAPI.validator().getValidInput("username",
                                    rs.getString("username"), "SafeString", 200, false)
                    );
                    user.setPassword(
                            ESAPI.validator().getValidInput("password",
                                    rs.getString("password"), "SafeString", 200, false)
                    );
                    user.setFirstName(
                            ESAPI.validator().getValidInput("firstname",
                                    rs.getString("firstname"), "SafeString", 200, false)
                    );
                    user.setLastName(
                            ESAPI.validator().getValidInput("lastname",
                                    rs.getString("lastname"), "SafeString", 200, false)
                    );
                    user.setEmail(
                            ESAPI.validator().getValidInput("Signup Email",
                                    rs.getString("email"), "Email", 200, false)
                    );
                    user.setAddress(
                            ESAPI.validator().getValidInput("address",
                                    rs.getString("address"), "SafeString", 200, false)
                    );
                    user.setCity(
                            ESAPI.validator().getValidInput("city",
                                    rs.getString("city"), "SafeString", 200, false)
                    );
                    user.setZip(
                            ESAPI.validator().getValidInput("zip",
                                    rs.getString("zip"), "SafeString", 200, false)
                    );
                    user.setCountry(
                            ESAPI.validator().getValidInput("country",
                                    rs.getString("country"), "SafeString", 200, false)
                    );
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User getUserProfile(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM users WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                if (ESAPI.validator().isValidInteger("userid",
                        Integer.toString(rs.getInt("userid")), 0, 100000, false)) {
                    user.setUserId(rs.getInt("userid"));
                } else {
                    user.setUserId(0);
                }
                try {
                    user.setUserName(
                            ESAPI.validator().getValidInput("username",
                                    rs.getString("username"), "SafeString", 200, false)
                    );
                    user.setPassword(
                            ESAPI.validator().getValidInput("password",
                                    rs.getString("password"), "SafeString", 200, false)
                    );
                    user.setFirstName(
                            ESAPI.validator().getValidInput("firstname",
                                    rs.getString("firstname"), "SafeString", 200, false)
                    );
                    user.setLastName(
                            ESAPI.validator().getValidInput("lastname",
                                    rs.getString("lastname"), "SafeString", 200, false)
                    );
                    user.setEmail(
                            ESAPI.validator().getValidInput("Signup Email",
                                    rs.getString("email"), "Email", 200, false)
                    );
                    user.setAddress(
                            ESAPI.validator().getValidInput("address",
                                    rs.getString("address"), "SafeString", 200, false)
                    );
                    user.setCity(
                            ESAPI.validator().getValidInput("city",
                                    rs.getString("city"), "SafeString", 200, false)
                    );
                    user.setZip(
                            ESAPI.validator().getValidInput("zip",
                                    rs.getString("zip"), "SafeString", 200, false)
                    );
                    user.setCountry(
                            ESAPI.validator().getValidInput("country",
                                    rs.getString("country"), "SafeString", 200, false)
                    );
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int addNewUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO users (username, password, firstname , "
                + "lastname, email, address, city, zip, country) "
                + "VALUES (?, MD5(CONCAT(?,'cd4tv5')), ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getZip());
            ps.setString(9, user.getCountry());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int removeUser(String userName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM users WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int updateUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE users SET "
                + "firstname = ?, "
                + "lastname = ?, "
                + "email = ?, "
                + "address = ?, "
                + "city = ?, "
                + "zip = ?, "
                + "country = ? "
                + "WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getCity());
            ps.setString(6, user.getZip());
            ps.setString(7, user.getCountry());
            ps.setString(8, user.getUserName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int validateUser(String user, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int output = 0;
        String query = "SELECT COUNT(*) > 0 AS output "
                + "FROM users WHERE username = ? AND password = MD5(CONCAT(?,'cd4tv5'))";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, password);
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

    public static int getUserId(String user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int output = 0;
        String query = "SELECT userid FROM users WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getInt("userid");
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
