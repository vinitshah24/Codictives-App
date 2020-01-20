package com.codictives.test;

import com.codictives.models.User;
import com.codictives.utility.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shahv
 */
@WebServlet(name = "TestUserDB", urlPatterns = {"/TestUserDB"})
public class TestUserDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        ArrayList<User> users = new UserDB().getUsers();
        out.println(users.size());
        for (int i = 0; i < users.size(); i++) {
            out.println("<p>" + users.get(i).getFirstName()
                    + " " + users.get(i).getLastName() + "</p>");
        }
        out.println("------------------------------------------------------");
        User userProfile = new UserDB().getUserProfile("user");
        out.println(userProfile);

        out.println("<p>" + userProfile.getUserID() + " "
                + userProfile.getUserName() + " "
                + userProfile.getFirstName() + " "
                + userProfile.getLastName() + " "
                + userProfile.getEmail() + " "
                + userProfile.getAddress() + " "
                + userProfile.getCity() + " "
                + userProfile.getZip() + " "
                + userProfile.getCountry() + "</p>");

        out.println("------------------------------------------------------");
        User newUser = new User("userNM", "userPass",
                "userF", "userL", "userEmail",
                "userAddress", "userCity", "userZip", "userCountry");
        int isAdded = new UserDB().addNewUser(newUser);
        out.println(isAdded);

        out.println("------------------------------------------------------");
        User updateUser = new User("userNM", "pass",
                "F", "L", "Email",
                "Address", "City", "Zip", "Country");
        int isUpdated = new UserDB().updateUser(updateUser);
        out.println(isUpdated);

        out.println("------------------------------------------------------");
        int isRemoved = new UserDB().removeUser("userNM");
        out.println(isRemoved);

        out.println("------------------------------------------------------");
        int isValidUser = new UserDB().validateUser("vinit", "vinit");
        out.println(isValidUser);

        out.println("------------------------------------------------------");
        
        String username = "user";
        User userID = new UserDB().getUserProfile(username);
        out.println(username + ": " + userID);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
