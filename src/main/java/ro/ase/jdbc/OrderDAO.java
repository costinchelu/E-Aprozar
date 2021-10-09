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
import ro.ase.entity.Order;

public class OrderDAO extends DAO<Order> {

    private Logger logger = Logger.getLogger(OrderDAO.class.getName());

    @Override
    public Long insert(Order order) {
        String query = "INSERT INTO orderuser (id_user, ordered_date, paid_date, status, customer_address, totalprice, paid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Long id = -1L;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, order.getIdUser());
            ps.setTimestamp(2, order.getOrderedDate());
            ps.setTimestamp(3, order.getPaidDate());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getCustomerAddress());
            ps.setDouble(6, order.getTotalPrice());
            ps.setBoolean(7, order.isPaid());
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
        String query = "DELETE FROM orderuser WHERE id_order=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            LineItemDAO lineItemDAO = new LineItemDAO();
            List<LineItem> lineitems = lineItemDAO.findAllByIdOrder(id);
            if (!lineitems.isEmpty()) {
                for (LineItem lineitem : lineitems) {
                    lineItemDAO.delete(lineitem.getIdLineItem());
                }
            }
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE orderuser SET id_user=?, ordered_date=?, paid_date=?, status=?, customer_address=?, totalprice=?, paid=? WHERE id_order=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, order.getIdUser());
            ps.setTimestamp(2, order.getOrderedDate());
            ps.setTimestamp(3, order.getPaidDate());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getCustomerAddress());
            ps.setDouble(6, order.getTotalPrice());
            ps.setBoolean(7, order.isPaid());
            ps.setLong(8, order.getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Order find(Long id) {
        Order order = null;
        String query = "SELECT * FROM orderuser WHERE id_order=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                order = new Order();
                order.setIdOrder(rs.getLong("id_order"));
                order.setIdUser(rs.getLong("id_user"));
                order.setOrderedDate(rs.getTimestamp("ordered_date"));
                order.setPaidDate(rs.getTimestamp("paid_date"));
                order.setStatus(rs.getString("status"));
                order.setCustomerAddress(rs.getString("customer_address"));
                order.setLineItem(new LineItemDAO().findAllByIdOrder(rs.getLong("id_order")));
                order.setTotalPrice(rs.getDouble("totalprice"));
                order.setPaid(rs.getBoolean("paid"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return order;
    }

    public List<Order> findAll() {
        List<Order> orderList = new LinkedList<>();
        String query = "SELECT * FROM orderuser";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order();
                order.setIdOrder(rs.getLong("id_order"));
                order.setIdUser(rs.getLong("id_user"));
                order.setOrderedDate(rs.getTimestamp("ordered_date"));
                order.setPaidDate(rs.getTimestamp("paid_date"));
                order.setStatus(rs.getString("status"));
                order.setCustomerAddress(rs.getString("customer_address"));
                order.setLineItem(new LineItemDAO().findAllByIdOrder(rs.getLong("id_order")));
                order.setTotalPrice(rs.getDouble("totalprice"));
                order.setPaid(rs.getBoolean("paid"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return orderList;
    }

    public List<Order> findAllByIdUser(Long idUser) {
        List<Order> orderList = new LinkedList<>();
        String query = "SELECT * FROM orderuser WHERE id_user=" + idUser;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order();
                order.setIdOrder(rs.getLong("id_order"));
                order.setIdUser(rs.getLong("id_user"));
                order.setOrderedDate(rs.getTimestamp("ordered_date"));
                order.setPaidDate(rs.getTimestamp("paid_date"));
                order.setStatus(rs.getString("status"));
                order.setCustomerAddress(rs.getString("customer_address"));
                order.setLineItem(new LineItemDAO().findAllByIdOrder(rs.getLong("id_order")));
                order.setTotalPrice(rs.getDouble("totalprice"));
                order.setPaid(rs.getBoolean("paid"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return orderList;
    }

    public List<Order> findAllByIdUserStatus(Long idUser, String status) {
        List<Order> orderList = new LinkedList<>();
        String query = "SELECT * FROM orderuser WHERE id_user=" + idUser + "AND status='" + status + "'" + "ORDER BY ordered_date";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order();
                order.setIdOrder(rs.getLong("id_order"));
                order.setIdUser(rs.getLong("id_user"));
                order.setOrderedDate(rs.getTimestamp("ordered_date"));
                order.setPaidDate(rs.getTimestamp("paid_date"));
                order.setStatus(rs.getString("status"));
                order.setCustomerAddress(rs.getString("customer_address"));
                order.setLineItem(new LineItemDAO().findAllByIdOrder(rs.getLong("id_order")));
                order.setTotalPrice(rs.getDouble("totalprice"));
                order.setPaid(rs.getBoolean("paid"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return orderList;
    }
}
