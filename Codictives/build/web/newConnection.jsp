<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                <h1 class="new-conn-h1">Create New Connection</h1>

                <form class="conn-form" action="/Codictives/ProfileController" method="post">
                    <label class="conn-lbl">Topic:</label><br>
                    <input class="conn-input" type="text" name="topic" required > <br>

                    <label class="conn-lbl">Name:</label><br>
                    <input class="conn-input" type="text" name="name" required ><br>

                    <label class="conn-lbl">Details:</label><br>
                    <textarea class="conn-txtarea" name="details" required ></textarea><br>

                    <label class="conn-lbl">Where:</label><br>
                    <input class="conn-input" type="text" name="location" required ><br>

                    <label class="conn-lbl">When:</label><br>
                    <input class="conn-input" type="date" name="date" required ><br>
                    <input class="conn-input" type="time" name="time" required >

                    <input type="hidden" value=${userSession} name="currentUser" />
                    <input type="hidden" value="newConnection" name="page" />

                    <label class="conn-lbl">&nbsp;</label><br>
                    <input class="conn-input" id="conn-submit" type="submit" value="Create Connection"/><br>
                </form>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>
