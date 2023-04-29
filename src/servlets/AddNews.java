package servlets;

import db.DBConnection;
import db.News;
import db.NewsCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add-news")
public class AddNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category = Integer.parseInt(request.getParameter("news_category_id"));
        String title = request.getParameter("news_title");
        String content = request.getParameter("news_content");
        NewsCategory newsCategory = DBConnection.getCategory(category);
        if(newsCategory!=null) {
            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setNewsCategory(newsCategory);
            DBConnection.addNews(news);
        }
        response.sendRedirect("/home");
    }
}
