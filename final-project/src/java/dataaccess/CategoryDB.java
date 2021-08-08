package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Category;

public class CategoryDB {

    public Category get(int id) throws Exception {
        Category category = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM category WHERE category_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);

                category = new Category(id, name);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return category;
    }

    public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM category";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);

                categories.add(new Category(id, name));
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return categories;
    }

    public void insert(Category category) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        String sql = "INSERT INTO category (`category_name`) "
                + "VALUES (?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void update(Category category) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE category "
                + "SET category_name = ?"
                + "WHERE category_id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }
}
