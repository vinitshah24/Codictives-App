package com.codictives.test;

import com.codictives.models.Connection;
import com.codictives.utility.ConnectionDB;
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
@WebServlet(name = "TestConnectionDB", urlPatterns = {"/TestConnectionDB"})
public class TestConnectionDB extends HttpServlet {

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

        ArrayList<Connection> con = new ConnectionDB().getConnections();
        out.println(con.size());
        for (int i = 0; i < con.size(); i++) {
            out.println("<p>" + con.get(i).getConnectionId()
                    + " " + con.get(i).getName() + " " + con.get(i).getOwnerId() + "</p>");
        }

        out.println("<p>------------------------------------------------------</p>");

        Connection newCon = new Connection(0, "name", "topic", "details",
                "loc", "2019-09-17", "18:00", 1);
        int isAdded = new ConnectionDB().addConnection(newCon);
        out.println(isAdded);

        out.println("<p>------------------------------------------------------</p>");

        Connection getCon = new ConnectionDB()
                .getConnection("Objected Oriented Programming with Java");
        out.println(getCon.getName() + " | "
                + getCon.getTopic() + " | " + getCon.getOwnerId());

        out.println("<p>------------------------------------------------------</p>");

        Connection removeCon = new Connection(0, "name", "topic", "details",
                "loc", "2019-09-17", "18:00", 1);
        int isRemoved = new ConnectionDB().removeConnection(removeCon);
        out.println(isRemoved);

        out.println("<p>------------------------------------------------------</p>");
        int conID = new ConnectionDB()
                .getConnectionId("Objected Oriented Programming with Java");
        out.println("CONNECTION ID: " + conID);

        out.println("<p>------------------------------------------------------</p>");
        conID = new ConnectionDB()
                .getConnectionId("Understanding Javascript");
        out.println("CONNECTION ID: " + conID);
        
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
