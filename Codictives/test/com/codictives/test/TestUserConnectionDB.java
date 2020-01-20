package com.codictives.test;

import com.codictives.models.ConnectionWithRsvp;
import com.codictives.utility.UserConnectionDB;

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
@WebServlet(name = "TestUserConnectionDB", urlPatterns = {"/TestUserConnectionDB"})
public class TestUserConnectionDB extends HttpServlet {

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

        ArrayList<ConnectionWithRsvp> connections = new ArrayList<ConnectionWithRsvp>();
        connections = new UserConnectionDB().getConnections(2);

        for (int i = 0; i < connections.size(); i++) {
            out.println("<p>" + connections.get(i).getName()
                    + " | " + connections.get(i).getOwnerId()
                    + " | " + connections.get(i).getUserId()
                    + " | " + connections.get(i).getRsvp() + "</p>");
        }

        out.println("<p>------------------------------------------------------</p>");

        int validateEntry = new UserConnectionDB().validateEntry(3, 1);
        out.println("VALIDATION: " + validateEntry);

        // If does NOT exists then only insert
        if (validateEntry == 0) {
            int isAdded = new UserConnectionDB().addConnection(3, 1, "maybe");
            out.println("INSERT IF NOT EXISTS: " + isAdded);
        }

        out.println("<p>------------------------------------------------------</p>");

        // If exists then update or else insert
        if (validateEntry == 1) {
            int isUpdated = new UserConnectionDB().updateConnection(1, 3, "maybe");
            out.println("UPDATED: " + isUpdated);
        } else {
            int isAdded = new UserConnectionDB().addConnection(1, 3, "maybe");
            out.println("UPDATE BUT NOT EXISTS SO INSERT: " + isAdded);
        }

        out.println("<p>------------------------------------------------------</p>");

        int isRemoved = new UserConnectionDB().removeConnection(2, 8);
        out.println("REMOVE: " + isRemoved);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
