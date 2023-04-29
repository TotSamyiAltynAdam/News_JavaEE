package db;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
                    "SELECT * " +
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
    public static ArrayList<NewsCategory> getCategories(){
        ArrayList<NewsCategory> newsCategoryArrayList = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM news_categories "
            );
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("id"));
                newsCategory.setName(resultSet.getString("name"));

                newsCategoryArrayList.add(newsCategory);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newsCategoryArrayList;
    }

    public static void addNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO news (category_id, post_date, title, content) " +
                      "VALUES (?, NOW(), ?, ?)"
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
    public static ArrayList<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
              "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.name " +
                      "FROM news n " +
                      "INNER JOIN news_categories nc ON n.category_id = nc.id " +
                      "ORDER BY n.post_date DESC"
            );
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                NewsCategory newsCategory = new NewsCategory();
                newsCategory.setId(resultSet.getInt("category_id"));
                newsCategory.setName(resultSet.getString("name"));

                News n = new News();
                n.setId(resultSet.getLong("id"));
                n.setPost_date(resultSet.getTimestamp("post_date"));
                n.setTitle(resultSet.getString("title"));
                n.setNewsCategory(newsCategory);
                n.setContent(resultSet.getString("content"));

                news.add(n);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
}
