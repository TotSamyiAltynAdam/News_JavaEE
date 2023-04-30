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
import java.util.List;

@WebServlet (value="/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NewsCategory> newsCategoryList = DBConnection.getCategories();
        request.setAttribute("newsCategories",newsCategoryList);

        String key = request.getParameter("key");
        List<News> newsList;
        if(key!=null) newsList = DBConnection.searchNews("%"+key+"%");
        else newsList = DBConnection.getNews();
        request.setAttribute("news",newsList);

        request.getRequestDispatcher("/main.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
