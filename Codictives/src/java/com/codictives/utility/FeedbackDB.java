package com.codictives.utility;

import com.codictives.models.Feedback;

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
 * @author shahv
 */
public class FeedbackDB {

    public static int addFeedback(Feedback feed) {
        ConnectionPool pool = ConnectionPool.getInstance();
        java.sql.Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO feedback (firstname,lastname,topic,message) "
                + "VALUES (?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, feed.getFirstName());
            ps.setString(2, feed.getLastName());
            ps.setString(3, feed.getTopic());
            ps.setString(4, feed.getMessage());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Feedback> getFeedbacks() {
        ArrayList<Feedback> feeds = new ArrayList<Feedback>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM feedback";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Feedback feed = null;
            while (rs.next()) {
                feed = new Feedback();
                try {
                    feed.setFirstName(
                            ESAPI.validator().getValidInput("firstName",
                                    rs.getString("firstName"), "SafeString", 200, false)
                    );
                    feed.setLastName(
                            ESAPI.validator().getValidInput("lastName",
                                    rs.getString("lastName"), "SafeString", 200, false)
                    );
                    feed.setTopic(
                            ESAPI.validator().getValidInput("topic",
                                    rs.getString("topic"), "SafeString", 200, false)
                    );
                    feed.setMessage(rs.getString("message").trim().replaceAll(" +", " "));
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(FeedbackDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                feeds.add(feed);
            }
            return feeds;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
