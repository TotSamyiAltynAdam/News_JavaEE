package servlets;

import db.Comment;
import db.DBConnection;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/remove-news")
public class RemoveNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = -1L;
        try {
            id = Long.valueOf(request.getParameter("news_id"));
        }catch (Exception e){
        }
        User currentUser = (User)request.getSession().getAttribute("currentUser");
        if(currentUser!=null && currentUser.getRole_id().equals("1")) {
            List<Comment> comments = DBConnection.getCommentsByNewsId(id);
            if (comments.size() != 0) DBConnection.removeCommentsByNewsId(id);
            DBConnection.removeNews(id);
        }
        response.sendRedirect("/");
    }
}
