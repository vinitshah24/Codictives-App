package com.codictives.servlets;

import com.codictives.models.Connection;
import com.codictives.models.ConnectionWithRsvp;
import com.codictives.models.Feedback;
import com.codictives.models.User;

import com.codictives.utility.UserConnectionDB;
import com.codictives.utility.ConnectionDB;
import com.codictives.utility.UserDB;
import com.codictives.utility.FeedbackDB;

import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 * Servlet to Display: Individual Connection Details, Removing Existing Connection to User
 * Account, Getting the saved connections of the user, Login and Sign Up Validation,
 * Profile Display, New Connection Creation, and Adding or modifying Connection to User
 * Account
 *
 * @author Vinit Shah
 */
public class ProfileControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
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

        HttpSession session = request.getSession();

        String url = "";
        String pageRequest = "";

        try {
            pageRequest = ESAPI.validator().getValidInput("Requested Page Validation",
                    request.getParameter("page"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileControllerServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        System.out.println("---------" + pageRequest + "---------");

        switch (pageRequest) {
            case "connections": {
                ArrayList<Connection> connections = ConnectionDB.getConnections();
                session.setAttribute("connections", connections);
                url = "/connections.jsp";
                break;
            }

            case "connection": {
                String currentConnection = "";
                try {
                    //Using ESAPI to validate request.getParameter("conName")
                    currentConnection = ESAPI.validator().getValidInput("Connection Name",
                            request.getParameter("conName"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                Connection connection = ConnectionDB.getConnection(currentConnection);
                session.setAttribute("connection", connection);
                url = "/connection.jsp";
                break;
            }

            case "savedConnections": {
                String username = "";
                try {
                    //Using ESAPI to validate request.getParameter("userSession")
                    username = ESAPI.validator().getValidInput("Connection Name",
                            request.getParameter("userSession"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                User userProfile = UserDB.getUserProfile(username);
                ArrayList<ConnectionWithRsvp> userConnections;
                userConnections = UserConnectionDB.getConnections(userProfile.getUserID());
                session.setAttribute("userConnections", userConnections);
                url = "/savedConnections.jsp";
                break;
            }

            case "updateConnection": {
                String connectionName = "";
                try {
                    //Using ESAPI to validate request.getParameter("connectionName")
                    connectionName = ESAPI.validator().getValidInput("Connection Name",
                            request.getParameter("connectionName"),
                            "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                request.setAttribute("page", "connection");
                Connection connection = ConnectionDB.getConnection(connectionName);
                session.setAttribute("connection", connection);
                url = "/connection.jsp";
                break;
            }

            case "deleteConnection": {
                String currentUser = "";
                String connectionName = "";
                try {
                    //Using ESAPI to validate request.getParameter("currentUser")
                    currentUser = ESAPI.validator().getValidInput("Current User",
                            request.getParameter("currentUser"),
                            "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionName")
                    connectionName = ESAPI.validator().getValidInput("Connection Name",
                            request.getParameter("connectionName"),
                            "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                int userid = UserDB.getUserId(currentUser);
                int connectionId = ConnectionDB.getConnectionId(connectionName);
                int isRemoved = UserConnectionDB.removeConnection(userid, connectionId);
                ArrayList<ConnectionWithRsvp> userConnections
                        = UserConnectionDB.getConnections(userid);
                session.setAttribute("userConnections", userConnections);
                url = "/savedConnections.jsp";
                break;
            }

            case "ownerConnections": {
                String currentUser = "";
                ArrayList<Connection> ownerConnections = null;
                try {
                    //Using ESAPI to validate request.getParameter("userSession")
                    currentUser = ESAPI.validator().getValidInput("UserName Session",
                            request.getParameter("userSession"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                int userid = UserDB.getUserId(currentUser);
                ownerConnections = ConnectionDB.getOwnerConnections(userid);
                request.setAttribute("ownerConnections", ownerConnections);
                url = "/ownerConnections.jsp";
                break;
            }

            case "editOwnerConnection": {
                String currentUser = " ", connectionName = " ";
                try {
                    //Using ESAPI to validate request.getParameter("currentUser")
                    currentUser = ESAPI.validator().getValidInput("Current User",
                            request.getParameter("currentUser"),
                            "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionName")
                    connectionName = ESAPI.validator().getValidInput("Connection Name",
                            request.getParameter("connectionName"),
                            "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                Connection con = ConnectionDB.getConnection(connectionName);
                request.setAttribute("connection", con);
                url = "/editConnection.jsp";
                break;
            }

            case "deleteOwnerConnection": {
                String currentUser = " ";
                int connectionId = 0;
                try {
                    //Using ESAPI to validate request.getParameter("currentUser")
                    currentUser = ESAPI.validator().getValidInput("Current User",
                            request.getParameter("currentUser"),
                            "SafeString", 200, false);
                    connectionId = Integer.parseInt(ESAPI.validator().
                            getValidInput("Current User",
                                    request.getParameter("connectionId"),
                                    "SafeString", 200, false));
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                int userId = UserDB.getUserId(currentUser);
                int result = ConnectionDB.removeOwnerConnection(connectionId, userId);
                url = "/index.jsp";
                break;
            }

            default:
                break;
        }
        getServletContext()
                .getRequestDispatcher(url).forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/index.jsp";
        String pageRequest = "";
        try {
            //Using ESAPI to validate request.getParameter("page")
            pageRequest = ESAPI.validator().getValidInput("Requested Page Validation",
                    request.getParameter("page"), "SafeString", 200, false);

        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileControllerServlet.class
                    .getName())
                    .log(Level.SEVERE, null, ex);
        }
        System.out.println("---------" + pageRequest + "---------");

        switch (pageRequest) {
            case "login":
                String loginUsername = "";
                String loginPassword = "";
                try {
                    //Using ESAPI to validate request.getParameter("username")
                    loginUsername = ESAPI.validator().getValidInput("Login Username",
                            request.getParameter("username"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("password")
                    loginPassword = ESAPI.validator().getValidInput("Login Password",
                            request.getParameter("password"), "SafeString", 200, false);

                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName())
                            .log(Level.SEVERE, null, ex);
                }
                if (loginUsername != null && loginPassword != null
                        && !loginUsername.isEmpty() && !loginPassword.isEmpty()) {
                    int isValidUser = UserDB.validateUser(
                            loginUsername.replaceAll("\\s+", "").trim(),
                            loginPassword.replaceAll("\\s+", "").trim()
                    );
                    if (isValidUser == 1) {
                        session.setAttribute("userSession", loginUsername);
                        User userProfile = UserDB.getUserProfile(loginUsername);
                        session.setAttribute("userProfile", userProfile);
                        url = "/index.jsp";
                    } else {
                        request.setAttribute("errorMessage",
                                "Invalid Username or Password!");
                        url = "/error.jsp";
                    }
                } else {
                    request.setAttribute("errorMessage", "Invalid Username or Password!");
                    url = "/error.jsp";
                }
                break;
            case "signup":
                String signupFirstName = "",
                 signupLastName = "",
                 signupUserName = "",
                 signupEmail = "",
                 signupAddress = "",
                 signupCity = "",
                 signupZip = "",
                 signupCountry = "",
                 signupPassword = "";
                try {
                    //Using ESAPI to validate request.getParameter("firstname")
                    signupFirstName = ESAPI.validator().getValidInput("Signup First Name",
                            request.getParameter("firstname"), "SafeString", 200, false);

                    //Using ESAPI to validate request.getParameter("lastname")
                    signupLastName = ESAPI.validator().getValidInput("Signup Last Name",
                            request.getParameter("lastname"), "SafeString", 200, false);

                    //Using ESAPI to validate request.getParameter("username")
                    signupUserName = ESAPI.validator().getValidInput("Signup User Name",
                            request.getParameter("username"), "SafeString", 200, false);

                    //Using ESAPI to validate request.getParameter("email")
                    signupEmail = ESAPI.validator().getValidInput("Signup Email",
                            request.getParameter("email"), "Email", 200, false);
                    //Using ESAPI to validate request.getParameter("address")
                    signupAddress = ESAPI.validator().getValidInput("Signup Address",
                            request.getParameter("address"), "SafeString", 200, false);

                    //Using ESAPI to validate request.getParameter("city")
                    signupCity = ESAPI.validator().getValidInput("Signup City",
                            request.getParameter("city"), "SafeString", 200, false);

                    //Using ESAPI to validate request.getParameter("zip")
                    signupZip = ESAPI.validator().getValidInput("Signup Zip",
                            request.getParameter("zip"), "SafeString", 200, false);

                    //Using ESAPI to validate request.getParameter("country")
                    signupCountry = ESAPI.validator().getValidInput("Signup Country",
                            request.getParameter("country"), "SafeString", 200, false);

                    //Using ESAPI to validate  request.getParameter("password")
                    signupPassword = ESAPI.validator().getValidInput("Signup Country",
                            request.getParameter("password"), "SafeString", 200, false);

                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }

                if (signupFirstName != null && signupLastName != null
                        && signupUserName != null && signupEmail != null
                        && signupAddress != null && signupCity != null
                        && signupZip != null && signupCountry != null
                        && signupPassword != null) {
                    if (signupFirstName.isEmpty() || signupLastName.isEmpty()
                            || signupUserName.isEmpty() || signupEmail.isEmpty()
                            || signupAddress.isEmpty() || signupCity.isEmpty()
                            || signupZip.isEmpty() || signupCountry.isEmpty()
                            || signupPassword.isEmpty()) {
                        request.setAttribute("errorMessage", "Invalid input fields "
                                + "for Signup form detected!");
                        url = "/error.jsp";
                    } else {
                        User newUser = new User(
                                signupUserName.replaceAll("\\s+", " ").trim(),
                                signupPassword.replaceAll("\\s+", " ").trim(),
                                signupFirstName, signupLastName,
                                signupEmail.replaceAll("\\s+", " ").trim(),
                                signupAddress, signupCity,
                                signupZip.replaceAll("\\s+", " ").trim(), signupCountry);
                        int isAdded = UserDB.addNewUser(newUser);
                        if (isAdded == 1) {
                            url = "/login.jsp";
                        } else {
                            request.setAttribute("errorMessage", "Signup Failed\n"
                                    + "Please fill out all the fields");
                            url = "/error.jsp";
                        }
                    }
                }
                break;

            case "newConnection": {
                String topic = "",
                        name = "",
                        details = "",
                        location = "",
                        date = "",
                        time = "",
                        currentUser = "";
                try {
                    //Using ESAPI to validate request.getParameter("topic")
                    topic = ESAPI.validator().getValidInput("Connection topic",
                            request.getParameter("topic"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("name")
                    name = ESAPI.validator().getValidInput("Connection name",
                            request.getParameter("name"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("details")
                    details = request.getParameter("details")
                            .trim().replaceAll("^ +| +$|( )+", "$1");
                    //Using ESAPI to validate request.getParameter("location")
                    location = ESAPI.validator().getValidInput("Connection location",
                            request.getParameter("location"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("date")
                    date = ESAPI.validator().getValidInput("Connection date",
                            request.getParameter("date"), "FileName", 200, false);
                    //Using ESAPI to validate request.getParameter("time")    
                    time = ESAPI.validator().getValidInput("Connection time",
                            request.getParameter("time"), "Time", 200, false);
                    //Using ESAPI to validate request.getParameter("currentUser")
                    currentUser = ESAPI.validator().getValidInput("currentUser",
                            request.getParameter("currentUser"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
                int currentUserId = UserDB.getUserId(currentUser);
                Connection con = new Connection(topic, name, details,
                        location, date, time, currentUserId);
                int isAdded = ConnectionDB.addConnection(con);
                if (isAdded == 1) {
                    ArrayList<Connection> connections = ConnectionDB.getConnections();
                    session.setAttribute("connections", connections);
                    url = "/connections.jsp";
                } else {
                    request.setAttribute("errorMessage", "Either Connection Already Exists"
                            + " Or Invalid Characters Found!");
                    url = "/error.jsp";
                }
                break;
            }
            case "connectionRsvp": {

                String currentUser = "", connectionName = "", rsvp = "";

                try {
                    //Using ESAPI to validate request.getParameter("currentUser")
                    currentUser = ESAPI.validator().getValidInput("Connection City",
                            request.getParameter("currentUser"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionName")
                    connectionName = ESAPI.validator().getValidInput("Connection City",
                            request.getParameter("connectionName"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionName")
                    rsvp = ESAPI.validator().getValidInput("Connection City",
                            request.getParameter("rsvp"), "SafeString", 200, false);

                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
                int userid = UserDB.getUserId(currentUser);
                int connectionId = ConnectionDB.getConnectionId(connectionName);
                int containsEntry = UserConnectionDB.validateEntry(userid, connectionId);
                if (containsEntry == 1) {
                    int isUpdated = UserConnectionDB
                            .updateConnection(userid, connectionId, rsvp);
                    //Set the session object again to reload savedCon jsp
                    ArrayList<ConnectionWithRsvp> userConnections
                            = UserConnectionDB.getConnections(userid);
                    session.setAttribute("userConnections", userConnections);
                    url = "/savedConnections.jsp";

                } else {
                    int isAdded = UserConnectionDB
                            .addConnection(userid, connectionId, rsvp);
                    ArrayList<ConnectionWithRsvp> userConnections
                            = UserConnectionDB.getConnections(userid);
                    session.setAttribute("userConnections", userConnections);
                    url = "/savedConnections.jsp";
                }
                break;
            }
            case "feedback": {
                String firstName = "", lastName = "", topic = "", message = "";
                try {
                    //Using ESAPI to validate request.getParameter("firstName")
                    firstName = ESAPI.validator().getValidInput("Connection firstname",
                            request.getParameter("firstName"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("lastName")
                    lastName = ESAPI.validator().getValidInput("Connection lastname",
                            request.getParameter("lastName"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("topic")
                    topic = ESAPI.validator().getValidInput("Connection topic",
                            request.getParameter("topic"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("message")
                    message = ESAPI.validator().getValidInput("Connection message",
                            request.getParameter("message"), "SafeString", 200, false);

                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
                Feedback feed = new Feedback(firstName, lastName, topic, message);
                int isAdded = FeedbackDB.addFeedback(feed);
                url = "/thankyou.jsp";
                break;
            }
            case "profile": {
                url = "/editProfile.jsp";
                break;
            }
            case "editProfile": {
                String userName = "", password = "", firstName = "", lastName = "",
                        email = "", address = "", city = "", zip = "", country = "";
                try {
                    //Using ESAPI to validate request.getParameter("topic")
                    userName = ESAPI.validator().getValidInput("EditProfile username",
                            request.getParameter("username"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("topic")
                    password = ESAPI.validator().getValidInput("EditProfile password",
                            request.getParameter("password"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("firstName")
                    firstName = ESAPI.validator().getValidInput("EditProfile firstName",
                            request.getParameter("firstname"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("lastname")
                    lastName = ESAPI.validator().getValidInput("EditProfile lastname",
                            request.getParameter("lastname"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("topic")
                    email = ESAPI.validator().getValidInput("Signup Email",
                            request.getParameter("email"), "Email", 200, false);
                    //Using ESAPI to validate request.getParameter("address")
                    address = ESAPI.validator().getValidInput("EditProfile address",
                            request.getParameter("address"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("city")
                    city = ESAPI.validator().getValidInput("EditProfile city",
                            request.getParameter("city"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("zip")
                    zip = ESAPI.validator().getValidInput("EditProfile zip",
                            request.getParameter("zip"), "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("country")
                    country = ESAPI.validator().getValidInput("EditProfile country",
                            request.getParameter("country"), "SafeString", 200, false);

                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
                User user = new User(userName, password, firstName, lastName,
                        email, address, city, zip, country);
                int isModified = UserDB.updateUser(user);
                User userProfile = UserDB.getUserProfile(userName);
                session.setAttribute("userProfile", userProfile);
                url = "/profile.jsp";
                break;
            }
            case "modifyConnection": {
                String currentUser = "", connectionName = "", connectionTopic = "",
                        connectionTime = "", connectionDate = "",
                        connectionLocation = "", connectionDetails = "";
                int connectionId = 0, connectionOwnerId = 0;
                try {
                    //Using ESAPI to validate request.getParameter("connectionId")
                    connectionId = Integer.parseInt(
                            ESAPI.validator().getValidInput("EditConn ID",
                                    request.getParameter("connectionId"),
                                    "SafeString", 200, false));
                    //Using ESAPI to validate request.getParameter("connectionName")
                    connectionName = ESAPI.validator().getValidInput("EditConn Name",
                            request.getParameter("connectionName"),
                            "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionTopic")
                    connectionTopic = ESAPI.validator().getValidInput("EditConn Topic",
                            request.getParameter("connectionTopic"),
                            "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionTime")
                    //connectionTime = request.getParameter("connectionTime");
                    connectionTime = ESAPI.validator().getValidInput("Connection time",
                            request.getParameter("connectionTime"), "Time", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionDate")
                    //connectionDate = request.getParameter("connectionDate");
                    connectionDate = ESAPI.validator().getValidInput("Connection date",
                            request.getParameter("connectionDate"), "FileName", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionLocation")
                    connectionLocation
                            = ESAPI.validator().getValidInput("EditConn Location",
                                    request.getParameter("connectionLocation"),
                                    "SafeString", 200, false);
                    //Using ESAPI to validate request.getParameter("connectionDetails")
                    connectionDetails = request.getParameter("connectionDetails")
                            .trim().replaceAll("^ +| +$|( )+", "$1");
                    //Using ESAPI to validate request.getParameter("connectionOwnerId")
                    connectionOwnerId = Integer.parseInt(
                            ESAPI.validator().getValidInput("EditConn ID",
                                    request.getParameter("connectionOwnerId"),
                                    "SafeString", 200, false));

                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileControllerServlet.class
                            .getName()).
                            log(Level.SEVERE, null, ex);
                }
                Connection con = new Connection(connectionId, connectionName,
                        connectionTopic, connectionDetails, connectionLocation,
                        connectionDate, connectionTime, connectionOwnerId);
                int isUpdated = ConnectionDB.updateOwnerConnection(con);
                url = "/index.jsp";
                break;
            }
            default:
                break;
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
