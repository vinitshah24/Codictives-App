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
                <h1>Connections</h1>
                <table class="saved-connections">
                    <tr>
                        <th class="con-spce">Category</th>
                        <th class="con-spce">Connections</th>
                    </tr>
                    <c:forEach items="${connections}" var="current">
                        <tr>
                            <td class="con-spce"><c:out value="${current.topic}" default="No Topic"/></td>
                            <td class="con-spce con-links links-color">
                                <a href=
                                   "<c:url value="/ProfileController">
                                       <c:param name="conName" value="${current.name}"/>
                                       <c:param name="page" value="connection"/>
                                   </c:url>" 
                                   class="con-links">
                                    ${current.name}
                                </a>
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