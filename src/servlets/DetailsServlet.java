package servlets;

import db.Comment;
import db.DBConnection;
import db.News;
import db.NewsCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = -1L;
        try {
            id = Long.valueOf(request.getParameter("news_id"));
        } catch (Exception e){
        }
        News news = DBConnection.getOneNews(id);
        request.setAttribute("foundNews",news);

        List<NewsCategory> categories = DBConnection.getCategories();
        request.setAttribute("categories",categories);

        List<Comment> comments = DBConnection.getCommentsByNewsId(id);
        request.setAttribute("comments",comments);

        request.getRequestDispatcher("/details.jsp").forward(request,response);
    }
}