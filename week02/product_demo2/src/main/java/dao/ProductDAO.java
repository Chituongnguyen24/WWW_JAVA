package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/shop_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mypass";

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

 public List<Product> getAllProducts() {
     String query = "select * from product";
     List<Product> products = new ArrayList<>();
     try (Connection con = getConnection();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(query)) {
         while (rs.next()) {
             int id = rs.getInt("id");
             String name = rs.getString("name");
             double price = rs.getDouble("price");
             String image = rs.getString("image");

             products.add(new Product(id,name,price,image));
         }
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
     return products;
 }

}
