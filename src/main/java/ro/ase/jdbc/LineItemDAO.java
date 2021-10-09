package ro.ase.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.ase.entity.LineItem;

public class LineItemDAO extends DAO<LineItem> {

    private Logger logger = Logger.getLogger(LineItemDAO.class.getName());

    @Override
    public Long insert(LineItem lineitem) {
        String query = "INSERT INTO lineitem (id_order, id_product, quanity, price, amount) VALUES (?, ?, ?, ?, ?)";
        Long id = -1L;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, lineitem.getIdOrder());
            ps.setLong(2, lineitem.getIdProduct());
            ps.setInt(3, lineitem.getQuantity());
            ps.setDouble(4, lineitem.getPrice());
            ps.setDouble(5, lineitem.getAmount());
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
        String query = "DELETE FROM lineitem WHERE id_lineitem=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(LineItem lineitem) {
        String query = "UPDATE lineitem SET id_order=?, id_product=?, quanity=?, price=?, amount=? WHERE id_lineitem=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, lineitem.getIdOrder());
            ps.setLong(2, lineitem.getIdProduct());
            ps.setInt(3, lineitem.getQuantity());
            ps.setDouble(4, lineitem.getPrice());
            ps.setDouble(5, lineitem.getAmount());
            ps.setLong(6, lineitem.getIdLineItem());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public LineItem find(Long id) {
        LineItem lineitem = null;
        String query = "SELECT * FROM lineitem WHERE id_lineitem=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                lineitem = new LineItem();
                lineitem.setIdLineItem(rs.getLong("id_lineitem"));
                lineitem.setIdOrder(rs.getLong("id_order"));
                lineitem.setIdProduct(rs.getLong("id_product"));
                lineitem.setProduct(new ProductDAO().find(rs.getLong("id_product")));
                lineitem.setQuantity(rs.getInt("quanity"));
                lineitem.setPrice(rs.getDouble("price"));
                lineitem.setAmount(rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return lineitem;
    }

    @Override
    public List<LineItem> findAll() {
        List<LineItem> lineitemList = new LinkedList<>();
        String query = "SELECT * FROM lineitem";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                LineItem lineitem = new LineItem();
                lineitem.setIdLineItem(rs.getLong("id_lineitem"));
                lineitem.setIdOrder(rs.getLong("id_order"));
                lineitem.setIdProduct(rs.getLong("id_product"));
                lineitem.setProduct(new ProductDAO().find(rs.getLong("id_product")));
                lineitem.setQuantity(rs.getInt("quanity"));
                lineitem.setPrice(rs.getDouble("price"));
                lineitem.setAmount(rs.getDouble("amount"));
                lineitemList.add(lineitem);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return lineitemList;
    }

    public List<LineItem> findAllByIdOrder(Long idOrder) {
        List<LineItem> lineitemList = new LinkedList<>();
        String query = "SELECT * FROM lineitem WHERE id_order=" + idOrder;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                LineItem lineitem = new LineItem();
                lineitem.setIdLineItem(rs.getLong("id_lineitem"));
                lineitem.setIdOrder(rs.getLong("id_order"));
                lineitem.setIdProduct(rs.getLong("id_product"));
                lineitem.setProduct(new ProductDAO().find(rs.getLong("id_product")));
                lineitem.setQuantity(rs.getInt("quanity"));
                lineitem.setPrice(rs.getDouble("price"));
                lineitem.setAmount(rs.getDouble("amount"));
                lineitemList.add(lineitem);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return lineitemList;
    }
}
