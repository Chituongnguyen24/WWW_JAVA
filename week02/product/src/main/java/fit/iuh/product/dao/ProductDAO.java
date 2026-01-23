package fit.iuh.product.dao;

import fit.iuh.product.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String JDBC_URL =
            "jdbc:mysql://localhost:3306/shop_db?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "mypass";

    private static final String SELECT_ALL =
            "SELECT * FROM products";
    private static final String SELECT_BY_ID =
            "SELECT * FROM products WHERE id = ?";
    private static final String INSERT =
            "INSERT INTO products(name, price, image) VALUES (?, ?, ?)";
    private static final String UPDATE =
            "UPDATE products SET name = ?, price = ?, image = ? WHERE id = ?";
    private static final String DELETE =
            "DELETE FROM products WHERE id = ?";

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Product> selectAllProducts() {
        List<Product> list = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("image")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ ONE
    public Product selectProduct(int id) {
        Product product = null;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("image")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    // CREATE
    public void insertProduct(Product product) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getImage());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateProduct(Product product) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getImage());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteProduct(int id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
