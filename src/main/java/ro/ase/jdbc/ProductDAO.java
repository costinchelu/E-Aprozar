package ro.ase.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.ase.entity.Product;

public class ProductDAO extends DAO<Product> {

    private Logger logger = Logger.getLogger(ProductDAO.class.getName());

    @Override
    public Long insert(Product product) {
        String query = "INSERT INTO product (department, name, price, currency, availability, description, specifications) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Long id = -1L;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getDepartment());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getCurrency());
            ps.setString(5, product.getAvailability());
            ps.setString(6, product.getDescription());
            ps.setString(7, specificationsToText(product.getSpecifications()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return id;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM product WHERE id_product=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE product SET department=?, name=?, price=?, currency=?, availability=?, description=?, specifications=? WHERE id_product=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, product.getDepartment());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getCurrency());
            ps.setString(5, product.getAvailability());
            ps.setString(6, product.getDescription());
            ps.setString(7, specificationsToText(product.getSpecifications()));
            ps.setLong(8, product.getIdProduct());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Product find(Long id) {
        Product product = null;
        String query = "SELECT * FROM product WHERE id_product=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getLong("id_product"));
                product.setDepartment(rs.getString("department"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCurrency(rs.getString("currency"));
                product.setAvailability(rs.getString("availability"));
                product.setDescription(rs.getString("description"));
                product.setSpecifications(textToSpecifications(rs.getString("specifications")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM product";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getLong("id_product"));
                product.setDepartment(rs.getString("department"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCurrency(rs.getString("currency"));
                product.setAvailability(rs.getString("availability"));
                product.setDescription(rs.getString("description"));
                product.setSpecifications(textToSpecifications(rs.getString("specifications")));
                productList.add(product);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return productList;
    }

    public List<Product> findAllByDepartment(String department) {
        List<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM product WHERE department='" + department + "'";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getLong("id_product"));
                product.setDepartment(rs.getString("department"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCurrency(rs.getString("currency"));
                product.setAvailability(rs.getString("availability"));
                product.setDescription(rs.getString("description"));
                product.setSpecifications(textToSpecifications(rs.getString("specifications")));
                productList.add(product);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return productList;
    }

    private String specificationsToText(List<String> specifications) {
        String text = "";
        for (int i = 0; i < specifications.size(); i++) {
            text += specifications.get(i);
            if (i < specifications.size() - 1) {
				text += "\n";
			}
        }
        return text;
    }

    private LinkedList<String> textToSpecifications(String specification) {
        LinkedList<String> strList = new LinkedList<>();
        strList.addAll(Arrays.asList(specification.split("\n")));
        return strList;
    }
}
