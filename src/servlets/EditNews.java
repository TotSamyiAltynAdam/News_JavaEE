package servlets;

import db.DBConnection;
import db.News;
import db.NewsCategory;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit-news")
public class EditNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute("currentUser");

        if(currentUser!=null && currentUser.getRole_id().equals("1")) {
            Long news_id = Long.parseLong(request.getParameter("news_id"));
            int category_id = Integer.parseInt(request.getParameter("news_category_id"));
            String title = request.getParameter("news_title");
            String content = request.getParameter("news_content");

            News news = DBConnection.getOneNews(news_id);
            NewsCategory newsCategory = DBConnection.getCategory(category_id);

            if (news != null && newsCategory != null) {
                news.setTitle(title);
                news.setContent(content);
                news.setNewsCategory(newsCategory);
                DBConnection.updateNews(news);
                response.sendRedirect("/details?news_id=" + news_id);
            } else {
                response.sendRedirect("/");
            }
        }else {
            response.sendRedirect("/");
        }
    }
}
