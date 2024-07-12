package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.WomensDAO;
import lk.carnage.carnagemanagementla.entity.Customer;
import lk.carnage.carnagemanagementla.entity.OrderDetail;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WomensDAOImpl implements WomensDAO {

    @Override
    public ArrayList<Womens> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Womens> allWomens = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product WHERE prod_id LIKE 'W%'");
        while (rst.next()) {
            Womens womens = new Womens(rst.getString("prod_id"), rst.getString("category"), rst.getDouble("price"),rst.getInt("qty"),rst.getString("season"));
            allWomens.add(womens);
        }
        return allWomens;
    }

    @Override
    public boolean add(Womens womens) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Product VALUES (?,?,?,?,?)", womens.getId(), womens.getCategory(), womens.getPrice(), womens.getQty(),womens.getSeason());

    }

    @Override
    public boolean update(Womens womens) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Product SET category = ?, price = ?, qty = ?, season = ? WHERE prod_id = ?",  womens.getCategory(), womens.getPrice(), womens.getQty(),womens.getSeason(), womens.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Product WHERE prod_id=?", id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT prod_id FROM Product ORDER BY prod_id DESC LIMIT 1;");
        if (rst.next()) {
            return rst.getString("prod_id");
        } else {
            return null;
        }
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT prod_id FROM Product");
        List<String> codeList = new ArrayList<>();
        while (rst.next()) {
            codeList.add(rst.getString(1));
        }
        return codeList;

    }

    @Override
    public String qtyCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT prod_id from Product WHERE qty <= 10");
        List<String> prodIds = new ArrayList<>();
        while (resultSet.next()) {
            prodIds.add(resultSet.getString("prod_id"));
        }
        return prodIds.toString();
    }

    @Override
    public Womens searchById(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Product WHERE prod_id = ?", code);
        if (resultSet.next()) {
            return new Womens(resultSet.getString("prod_id"), resultSet.getString("category"), resultSet.getDouble("price"),resultSet.getInt("qty"),resultSet.getString("season"));
        }
        return null;
    }

    @Override
    public boolean update(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getItemCode(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;

    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Product SET qty = qty - ? WHERE prod_id = ?", qty, itemCode);
    }
}
