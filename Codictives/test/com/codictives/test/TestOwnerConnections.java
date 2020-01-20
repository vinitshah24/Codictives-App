package com.codictives.test;

import com.codictives.models.Connection;
import com.codictives.models.ConnectionWithRsvp;
import com.codictives.utility.ConnectionDB;
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
public class TestOwnerConnections extends HttpServlet {

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
        ArrayList<Connection> connections = new ArrayList<Connection>();
        connections = ConnectionDB.getOwnerConnections(1);
        for(int i =0; i< connections.size(); i++){
            out.println("<p>" + connections.get(i).getName() + " " + 
                    connections.get(i).getTopic() + "</p>");
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}