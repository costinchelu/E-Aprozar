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

import ro.ase.entity.AuthorityType;
import ro.ase.entity.InfoUser;
import ro.ase.entity.Order;

public class InfoUserDAO extends DAO<InfoUser> {

    private Logger logger = Logger.getLogger(InfoUserDAO.class.getName());

    public void save(InfoUser user) {
        String query = "INSERT INTO infouser (name, email, password, enabled, authority) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isEnabled());
            ps.setString(5, user.getAuthority().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Long insert(InfoUser user) {
        String query = "INSERT INTO infouser (surname, name, details, email, password, phone, status, enabled, authority) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Long id = -1L;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getSurname());
            ps.setString(2, user.getName());
            ps.setString(3, user.getDetails());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getStatus());
            ps.setBoolean(8, user.isEnabled());
            ps.setString(9, user.getAuthority().name());
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
        String query = "DELETE FROM infouser WHERE id_user=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.findAllByIdUser(id);
            if (!orders.isEmpty()) {
                for (Order order : orders) {
                    orderDAO.delete(order.getIdOrder());
                }
            }
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(InfoUser user) {
        String query = "UPDATE infouser SET surname=?, name=?, details=?, email=?, password=?, phone=?, status=?, enabled=?, authority=? WHERE id_user=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getSurname());
            ps.setString(2, user.getName());
            ps.setString(3, user.getDetails());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getStatus());
            ps.setBoolean(8, user.isEnabled());
            ps.setString(9, user.getAuthority().name());
            ps.setLong(10, user.getIdUser());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public InfoUser find(Long id) {
        InfoUser user = null;
        String query = "SELECT * FROM infouser WHERE id_user=" + id;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                user = new InfoUser();
                user.setIdUser(rs.getLong("id_user"));
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDetails(rs.getString("details"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status"));
                user.setOrders(new OrderDAO().findAllByIdUser(rs.getLong("id_user")));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setAuthority(Enum.valueOf(AuthorityType.class, rs.getString("authority")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return user;
    }

    public InfoUser findByEmail(String email) {
        InfoUser user = null;
        String query = "SELECT * FROM infouser WHERE email='" + email + "'";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                user = new InfoUser();
                user.setIdUser(rs.getLong("id_user"));
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDetails(rs.getString("details"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status"));
                user.setOrders(new OrderDAO().findAllByIdUser(rs.getLong("id_user")));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setAuthority(Enum.valueOf(AuthorityType.class, rs.getString("authority")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return user;
    }

    public InfoUser findByPhone(String phone) {
        InfoUser user = null;
        String query = "SELECT * FROM infouser WHERE phone='" + phone + "'";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                user = new InfoUser();
                user.setIdUser(rs.getLong("id_user"));
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDetails(rs.getString("details"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status"));
                user.setOrders(new OrderDAO().findAllByIdUser(rs.getLong("id_user")));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setAuthority(Enum.valueOf(AuthorityType.class, rs.getString("authority")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return user;
    }

    @Override
    public List<InfoUser> findAll() {
        List<InfoUser> userList = new LinkedList<>();
        String query = "SELECT * FROM infouser";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                InfoUser user = new InfoUser();
                user.setIdUser(rs.getLong("id_user"));
                user.setSurname(rs.getString("surname"));
                user.setName(rs.getString("name"));
                user.setDetails(rs.getString("details"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getString("status"));
                user.setOrders(new OrderDAO().findAllByIdUser(rs.getLong("id_user")));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setAuthority(Enum.valueOf(AuthorityType.class, rs.getString("authority")));
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return userList;
    }
}
