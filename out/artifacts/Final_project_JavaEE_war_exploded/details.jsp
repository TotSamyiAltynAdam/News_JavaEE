<%@ page import="db.News" %>
<%@ page import="db.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <%@include file="navbar.jsp"%>
    <div class="container mt-4 w-75">

        <%
            News news = (News) request.getAttribute("foundNews");
            if(news!=null){
                if(currentUser!=null){
        %>
                <div class="row mt-3">
                    <div class="col-12">
                        <div class="row m-0">
                            <div class="col-12 p-0">
                                <div class="card mb-3 border border-3 border-warning text-warning" style="background-color: <%=mainRedColor%>">
                                    <div class="row g-0">
                                        <div class="col-md-4">
                                            <img src="images/<%=news.getNewsCategory().getId()%>.jpg" class="img-fluid rounded-start w-100" alt="typeOfNews" style="max-height: 200px;">
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body">
                                                <h5 class="card-title"><%=news.getTitle()%></h5>
                                                <p class="card-text"><%=news.getContent()%></p>
                                                <p class="card-text"><small class="text-body-secondary"><%=news.getPost_date()%></small></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-3 mb-3">
                        <%
                            if(currentUser.getRole_id().equals("1")){
                        %>
                        <button type="button" class="btn btn-warning mt-3" data-bs-toggle="modal" data-bs-target="#editTask">
                            Change
                        </button>
                        <button type="button" class="btn btn-danger mt-3 ms-3 me-3"
                              data-bs-toggle="modal" data-bs-target="#removeTask">Delete
                        </button>
                        <%
                            }
                        %>
                        <a href="/">
                            <button type="button" class="btn btn-secondary mt-3" data-bs-dismiss="modal">Close</button>
                        </a>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-6 mb-3">
                        <form action="/add-comment" method="post">
                            <input type="hidden" name="news_id" value="<%=news.getId()%>">
                            <div class="row">
                                <div class="col-12">
                                    <textarea class="form-control" name="comment" required></textarea>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-12">
                                    <button class="btn btn-warning btn-sm">Add comment</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <div class="list-group">
                            <%
                                ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                                if(comments!=null){
                                    for(Comment c: comments){
                            %>
                                    <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                                        <div class="d-flex w-100 justify-content-between">
                                            <h5 class="mb-1"><%=c.getUser().getFullName()%>></h5>
                                            <small class="text-body-secondary"><%=c.getPost_date()%></small>
                                        </div>
                                        <p class="mb-1"><%=c.getComment()%></p>
                                    </a>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>

                <%
                    if(currentUser.getRole_id().equals("1")){
                %>
                    <form action="/remove-news" method="post">
                        <input type="hidden" name="news_id" value="<%=news.getId()%>">
                        <div class="modal fade" id="removeTask" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">Verification removing</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h5 class="text-center">Are you sure ?</h5>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-danger">Remove</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal fade" id="editTask" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog modal-lg">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h1 class="modal-title fs-5">Change News</h1>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                              <%@include file="editNews.jsp"%>
                            </div>
                          </div>
                      </div>
                    </div>
        <%
                    }
                }else{
                    response.sendRedirect("/login");
                }
            } else {
        %>
                <h3 class="text-center">
                    News Not Found
                </h3>
        <%
            }
        %>
    </div>
</body>
</html>
