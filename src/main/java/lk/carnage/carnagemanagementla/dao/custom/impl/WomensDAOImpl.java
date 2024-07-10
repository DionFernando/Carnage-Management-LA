package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.WomensDAO;
import lk.carnage.carnagemanagementla.entity.Customer;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        return SQLUtil.execute("UPDATE Product SET category = ?, price = ?, qty = ?, season = ? WHERE prod_id = ?", womens.getId(), womens.getCategory(), womens.getPrice(), womens.getQty(),womens.getSeason());
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
}
