package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/news_db",
                    "root",
                    "ec2-hTS-BMm-fUT"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM users " +
                            "WHERE email = ?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole_id(String.valueOf(resultSet.getLong("role_id")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void addUser(User user){
        try {
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO users(email,password,full_name,role_id) " +
                      "VALUES (?,?,?,?)"
            );
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullName());
            statement.setString(4,user.getRole_id());

            statement.executeUpdate();
                statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET " +
                            "email = ?, " +
                            "password = ?, " +
                            "full_name = ?, " +
                            "role_id = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getRole_id());
            statement.setLong(5, user.getId());
            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static NewsCategory getCategory(int id){
        NewsCategory newsCategory = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name " +
                            "FROM news_categories " +
                            "WHERE id = ?"
            );
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newsCategory;
    }
    public static void addNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO news (post_date,category_id,title,content) " +
                      "VALUES (NOW(),?,?,?)"
            );
            statement.setInt(1,news.getNewsCategory().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
