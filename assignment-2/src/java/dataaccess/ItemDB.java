package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Item;

public class ItemDB {

    public List<Item> getAll(String email) throws Exception {
        List<Item> items = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM item where owner = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt(1);
                int category = rs.getInt(2);
                String itemName = rs.getString(3);
                double price = rs.getDouble(4);
                String owner = rs.getString(5);

                items.add(new Item(itemId, category, itemName, price, owner));
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return items;
    }

    public void insert(Item item) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        String sql = "INSERT INTO item (`category`,`item_name`,`price`,`owner`) "
                + "VALUES (?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory());
            ps.setString(2, item.getItemName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    }

    public void delete(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM item WHERE item_id = ? and owner = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            ps.setString(2, item.getOwner());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
