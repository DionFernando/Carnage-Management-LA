package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.Customer;
import lk.carnage.carnagemanagementla.entity.OrderDetail;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WomensDAO extends CrudDAO<Womens> {
    public String generateID() throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;

    public String qtyCount() throws SQLException, ClassNotFoundException;

    public Womens searchById(String code) throws SQLException, ClassNotFoundException;

    public boolean update(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;
}
