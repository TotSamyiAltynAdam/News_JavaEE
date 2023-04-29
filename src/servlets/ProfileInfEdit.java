package servlets;

import db.DBConnection;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/profileInfEdit")
public class ProfileInfEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        User user = DBConnection.getUser(email);
        if(user!=null){
            user.setFullName(fullName);
            user.setPassword(password);

            DBConnection.updateUser(user);
            response.sendRedirect("/profile");
        }else{
            response.sendRedirect("/");
        }
    }
}
