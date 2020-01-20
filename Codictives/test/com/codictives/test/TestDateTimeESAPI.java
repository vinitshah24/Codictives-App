/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codictives.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author shahv
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class TestDateTimeESAPI extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String date = "2020-05-28";
        String time = "13:00";
        String email = "vinit@meetup.com";
        String summary = "NodeJS event is focused on the entire Node.js ecosystem where, the speakers, will be sharing, latest news, and information, through talks, workshops, and their experiences within the Node community. The event will include introductory remarks! and training? sessions from the industry professionsls. egruhbg grheg regv vrhj rbvrju bvri98y gb v890bvg kjn4g9580hbkj gv49k 490hg u7kh5r9gij4o 904hg53 7u904h57 h35r490uh7 ol39h0u7g 34. NodeJS event is focused on the entire Node.js ecosystem where, the speakers, will be sharing, latest news, and information, through talks, workshops, and their experiences within the Node community. The event will include introductory remarks! and training? sessions from the industry professionsls.NodeJS event is focused on the entire Node.js ecosystem where, the speakers, will be sharing, latest news, and information, through talks, workshops, and their experiences within the Node community. The event will include introductory remarks! and training? sessions from the industry professionsls. egruhbg grheg regv vrhj rbvrju bvri98y gb v890bvg kjn4g9580hbkj gv49k 490hg u7kh5r9gij4o 904hg53 7u904h57 h35r490uh7 ol39h0u7g 34. NodeJS event is focused on the entire Node.js ecosystem where, the speakers, will be sharing, latest news, and information, through talks, workshops, and their experiences within the Node community. The event will include introductory remarks! and training? sessions from the industry professionsls.";
        String testDate = "", testTime = "", testEmail = "", testSummary = "";
         boolean testInt = false;
         int integerTest = 241;
        try {
            testDate = ESAPI.validator().getValidInput("test", date, "FileName", 200, false);
            testTime = ESAPI.validator().getValidInput("test", time, "Time", 200, false);
            testEmail = ESAPI.validator().getValidInput("test", email, "Email", 200, false);
            testSummary = ESAPI.validator().getValidInput("test", summary, "Text", 2000000, false);
            testInt = ESAPI.validator().isValidInteger("test", "xwre"
                    /*Integer.toString(integerTest)*/, 0, 100000, false);

        } catch (ValidationException ex) {
            Logger.getLogger(TestDateTimeESAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrusionException ex) {
            Logger.getLogger(TestDateTimeESAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.println("<h1>Date: " + testDate + "</h1>");
        out.println("<h1>Time: " + testTime + "</h1>");
        out.println("<h1>Email: " + testEmail + "</h1>");
        out.println("<h1>Summary: " + testSummary + "</h1>");
        out.println("<h1>IS Int: " + testInt + "</h1>");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
