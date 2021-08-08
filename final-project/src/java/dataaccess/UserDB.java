package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserDB {

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                int company = rs.getInt(7);
                users.add(new User(email, active, firstName, lastName, password, role, company));
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return users;
    }

    public List<User> getAllByCompany(int companyId) throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user where company = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, companyId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                int company = rs.getInt(7);
                users.add(new User(email, active, firstName, lastName, password, role, company));
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return users;
    }

    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM user WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                int company = rs.getInt(7);
                user = new User(email, active, firstName, lastName, password, role, company);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return user;
    }

    public void insert(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        String sql = "INSERT INTO user (`email`,`active`,`first_name`,`last_name`,`password`,`role`,`company`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole());
            ps.setInt(7, user.getCompany());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user "
                + "SET email = ?,"
                + "active = ?,"
                + "first_name = ?,"
                + "last_name = ?,"
                + "password = ?,"
                + "role = ? ,"
                + "company = ? "
                + "WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole());
            ps.setInt(7, user.getCompany());
            ps.setString(8, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE email = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }
}
