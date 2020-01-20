<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Codictives</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
        <link rel="shortcut icon" href="images/bash_logo.png" />
    </head>

    <body>
        <div class="content">
            <!-- Header User Specific Nav -->
            <jsp:include page="header.jsp"/>

            <!-- Main Content-->
            <div class="con-div">

                <div class="textholder2">
                    <h1 class="title"><c:out value="${connection.name}" default="No Title"/></h1>
                    <img src="images/dev.jpeg" alt="My test image" class="team-pic">
                    <h2>Details:</h2>
                    <p><b>Time: </b><c:out value="${connection.time}" default="No Time"/></p>
                    <p><b>Date: </b><c:out value="${connection.date}" default="No Date"/></p>
                    <p><b>Location: </b><c:out value="${connection.location}" default="No Location"/></p>
                    <h2>Highlights:</h2>
                    <p><c:out value="${connection.details}" default="No Details"/></p>
                    <div class="connection-options">
                        <c:choose>
                            <c:when test="${empty userSession}">
                                <div class="connection-options">
                                    <br>
                                    <p>PLEASE LOGIN OR SIGNUP TO RSVP FOR THE CONNECTIONS</p>
                                    <br>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p>Are you attending the conference? <i id="ppl-going"></i></p>
                                <form method="post" name="form" action="/Codictives/ProfileController" 
                                      class="status-btn-form" >
                                    <input type="hidden" name="page" value="connectionRsvp" />
                                    <input type="hidden" name="currentUser" value="${userSession}" />
                                    <input type="hidden" name="connectionName" value="${connection.name}" />
                                    <input type="hidden" name="rsvp" value="yes" />
                                    <button class="yes-button">Yes</button>
                                </form>
                                <form method="post" name="form" action="/Codictives/ProfileController" 
                                      class="status-btn-form" >
                                    <input type="hidden" name="page" value="connectionRsvp" />
                                    <input type="hidden" name="currentUser" value="${userSession}" />
                                    <input type="hidden" name="connectionName" value="${connection.name}" />
                                    <input type="hidden" name="rsvp" value="no" />
                                    <button class="no-button">No</button>
                                </form>
                                <form method="post" name="form" action="/Codictives/ProfileController" 
                                      class="status-btn-form" >
                                    <input type="hidden" name="page" value="connectionRsvp" />
                                    <input type="hidden" name="currentUser" value="${userSession}" />
                                    <input type="hidden" name="connectionName" value="${connection.name}">
                                    <input type="hidden" name="rsvp" value="maybe" />
                                    <button class="maybe-button">Maybe</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>