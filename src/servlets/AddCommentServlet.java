package servlets;

import db.Comment;
import db.DBConnection;
import db.News;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null){
            String commentText = request.getParameter("comment");
            Long newsId = Long.parseLong(request.getParameter("news_id"));

            News news = DBConnection.getOneNews(newsId);

            if(news!=null){
                Comment comment = new Comment();
                comment.setComment(commentText);
                comment.setUser(user);
                comment.setNews(news);

                DBConnection.addComment(comment);
                response.sendRedirect("/details?news_id="+newsId);
            }else{
                response.sendRedirect("/");
            }

        }else{
            response.sendRedirect("/login");
        }
    }

}