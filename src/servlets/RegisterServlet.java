package servlets;

import db.User;
import db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repPassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");

        User user = DBConnection.getUser(email);
        if(user == null){
            if(password.equals(repPassword)){

                User newUser = new User();
                newUser.setEmail(email);
                newUser.setFullName(fullName);
                newUser.setPassword(password);
                newUser.setRole_id("2");

                DBConnection.addUser(newUser);
                response.sendRedirect("/register?success");
            }else{
                response.sendRedirect("/register?passwordError");
            }
        }else{
            response.sendRedirect("/register?emailError");
        }
    }
}
