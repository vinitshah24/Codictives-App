<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="conn-div">

                <h1 class="new-conn-h1">Update Connection</h1>
                <form class="conn-form" action="/Codictives/ProfileController" method="post">

                    <input type="hidden" name="connectionId" value="${connection.connectionId}" /> 
                    <br>
                    <label class="conn-lbl">Connection Name</label><br>
                    <input class="conn-input" type="text" name="connectionName" 
                           value="${connection.name}" required /> 
                    <br>
                    <label class="conn-lbl">Connection Topic</label><br>
                    <input class="conn-input" type="text" name="connectionTopic" 
                           value="${connection.topic}" required /> 
                    <br>
                    <label class="conn-lbl">Time</label><br>
                    <input class="conn-input" type="time" name="connectionTime" 
                           value="${connection.time}" required />
                    <label class="conn-lbl">Date</label><br>
                    <input class="conn-input" type="date" name="connectionDate" 
                           value="${connection.date}" required />
                    <br>
                    <label class="conn-lbl">Location</label><br>
                    <input class="conn-input" type="text" name="connectionLocation" 
                           value="${connection.location}" required />
                    <br>
                    <label class="conn-lbl">Details</label><br>
                    <textarea class="conn-txtarea" name="connectionDetails" 
                              required>${connection.details}
                    </textarea>
                    <br>
                    <input type="hidden" name="connectionOwnerId" value="${connection.ownerId}" />
                    <input type="hidden" name="currentUser" value="${userSession}" />
                    <input type="hidden" name="page" value="modifyConnection" />

                    <label class="conn-lbl">&nbsp;</label><br>
                    <input class="conn-input" id="conn-submit" type="submit" value="Update Connection"/><br>
                </form>
            </div>
        </div>
                   
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>