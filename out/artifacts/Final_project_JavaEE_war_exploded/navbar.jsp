<%@ page import="db.User" %><%
    User currentUser = (User) session.getAttribute("currentUser");
%>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <div class="container p-0">
        <nav class="navbar navbar-expand-lg bg-body-tertiary border border-info border-warning border-2 rounded" style="background-color: <%=mainRedColor%>">
            <div class="container-fluid">
                <a class="navbar-brand text-warning fw-bold" style="font-size: 30px" href="/"><%=siteName%></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent" style="display: flex; justify-content: space-between;">
                    <div>
                        <form class="d-flex mt-3" role="search" action="/home" method="get">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-warning" type="submit">Search</button>
                        </form>
                    </div>

                    <div>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <%
                        if(currentUser!=null){
                    %>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-warning" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <%=currentUser.getFullName()%>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item text-warning" href="/profile">Profile</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item text-warning" href="/logout">Logout</a></li>
                                </ul>
                            </li>
                    <%
                        } else {
                    %>
                            <li class="nav-item">
                                <a class="nav-link text-warning" aria-current="page" href="/register">Register</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-warning" aria-current="page" href="/login">Login</a>
                            </li>
                    <%
                        }
                    %>
                        </ul>
                    </div>

                </div>
            </div>
        </nav>
    </div>
</body>
</html>
