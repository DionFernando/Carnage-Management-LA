package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.MensDAO;
import lk.carnage.carnagemanagementla.entity.Mens;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MensDAOImpl implements MensDAO {
    @Override
    public ArrayList<Mens> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Mens> allMens = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product WHERE prod_id LIKE 'M%'");
        while (rst.next()) {
            Mens mens = new Mens(rst.getString("prod_id"), rst.getString("category"), rst.getDouble("price"),rst.getInt("qty"),rst.getString("season"));
            allMens.add(mens);
        }
        return allMens;
    }

    @Override
    public boolean add(Mens mens) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Product VALUES (?,?,?,?,?)", mens.getId(), mens.getCategory(), mens.getPrice(), mens.getQty(),mens.getSeason());
    }

    @Override
    public boolean update(Mens mens) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Product SET category = ?, price = ?, qty = ?, season = ? WHERE prod_id = ?", mens.getId(), mens.getCategory(), mens.getPrice(), mens.getQty(),mens.getSeason());
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
