<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
            <div class="saved-con-div">
                <h1>Your Saved Connection</h1>
                <table class="saved-connections">
                    <tr>
                        <th>Connections</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Modifications</th>
                    </tr>

                    <c:forEach items="${userConnections}" var="connection">
                        <tr>
                            <c:choose>
                                <c:when test="${connection.ownerId==connection.userId}">
                                    <td><c:out value="${connection.name}" default="No Title"/>
                                        <img src="images/verification.png" alt="created by user" class="verification">
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>${connection.name}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${connection.topic}</td>
                            <td>${connection.rsvp}</td>

                            <td>
                                <form class="con-links">
                                    <a class="update-button" href=
                                       "<c:url value="/ProfileController">
                                           <c:param name="page" value="updateConnection"/>
                                           <c:param name="connectionName" value="${connection.name}"/>
                                           <c:param name="currentUser" value="${userSession}"/>
                                       </c:url>"
                                       >Update</a>

                                    <a class="delete-button" href=
                                       "<c:url value="/ProfileController">
                                           <c:param name="page" value="deleteConnection"/>
                                           <c:param name="connectionName" value="${connection.name}"/>
                                           <c:param name="currentUser" value="${userSession}"/>
                                       </c:url>" 
                                       >Delete</a>                              
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>