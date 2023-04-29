<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="navbar.jsp"%>
    <div class="container mt-3">
        <div class="row">
            <%@include file="profile_form.jsp"%>
            <div class="col-9 border border-2 border-warning" style="border-radius: 0 10px 10px 0;">
                <form action="/profileInfEdit" method="post">
                    <div class="form-group row mt-2">
                        <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticEmail" value="<%=currentUser.getEmail()%>" name="email">
                        </div>
                    </div>
                    <div class="form-group row mt-3">
                        <label for="fullName" class="col-sm-2 col-form-label">FullName</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="fullName" placeholder="Name Surname" name="fullName" value="<%=currentUser.getFullName()%>">
                        </div>
                    </div>
                    <div class="form-group row mt-3">
                        <label for="password" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                        </div>
                    </div>
                    <div class="form-control row w-25 mt-3" style="margin: 0 auto">
                        <button type="submit" class="btn btn-warning mb-2">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
