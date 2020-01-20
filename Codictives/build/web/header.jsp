<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="user-specific-nav">
        <div>
            <a href="index.jsp"><img id="logo" src="images/logo.png" alt="logo"></a>
        </div>
        <form action="/Codictives/InvalidateSessionServlet" method="POST" class="nav-links">
            <input type="hidden" name="invalidate" value="${userSession}"/>
            <ul>
                <c:choose>
                    <c:when test="${empty userSession}">
                        <li>${userSession}</li>
                        <li class="nav-item"><a class="chngNav1" href="login.jsp">Login</a></li>
                        <li class="nav-item"><a class="chngNav2" href="signup.jsp">Sign Up</a></li>                                      
                        </c:when>    
                        <c:otherwise>
                        <li class="nav-item"><a href="newConnection.jsp">New Connection</a></li>
                        <li class="nav-item">
                            <a href=
                               "/Codictives/ProfileController?page=savedConnections&userSession=${userSession}">
                                My Connections
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" onclick="document.forms[0].submit();return false;">Sign Out</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </form>
    </nav>
    <!--Header General Nav-->
    <nav>
        <ul class="nav-gen-ul">
            <c:choose>
                <c:when test="${empty userSession}">
                    <!--li class="nav-gen"><a href="index.jsp">Home</a></li-->
                    <!--li class="nav-gen"><a href="about.jsp">About</a></li -->
                    <li class="nav-gen">
                        <a href="/Codictives/ProfileController?page=connections">Available Connections</a>
                    </li>
                    </c:when>
                    <c:otherwise>
                    <!--li class="nav-gen"><a href="index.jsp">Home</a></li-->
                    <!--li class="nav-gen"><a href="about.jsp">About</a></li-->
                    <li class="nav-gen">
                        <a href="/Codictives/ProfileController?page=connections">Available Connections</a>
                    </li>
                    <li class="nav-gen"><a href="profile.jsp">My Profile</a></li>
                    <li class="nav-gen">
                        <a href="/Codictives/ProfileController?page=ownerConnections&userSession=${userSession}">
                            Created Connections</a>
                    </li>
                    <li class="nav-display">Welcome ${userSession}</li>
                    </c:otherwise>
                </c:choose>
        </ul>
    </nav>
</header>