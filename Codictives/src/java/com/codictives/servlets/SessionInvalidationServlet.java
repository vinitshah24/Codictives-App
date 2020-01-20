package com.codictives.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 * Servlet Class to invalidate the Session for the application
 *
 * @author Vinit Shah
 */
public class SessionInvalidationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * doGet method to invalidate the session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("");
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * doPost method to invalidate the session
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Invalidating the Session
        HttpSession session = request.getSession(false);
        if (session != null) {
            try {
                String invalidate
                        = ESAPI.validator().getValidInput("Requested Page Validation",
                                request.getParameter("invalidate"),
                                "SafeString", 200, false);
                if (invalidate != null) {
                    System.out.println("---------logout---------");
                    request.getSession().invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(SessionInvalidationServlet.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
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