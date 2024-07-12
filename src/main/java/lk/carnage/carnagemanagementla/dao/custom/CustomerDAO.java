package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    public String generateID() throws SQLException, ClassNotFoundException;

    public Customer searchByTel(String id) throws SQLException, ClassNotFoundException;

    public List<String> getCustomerAddress() throws SQLException, ClassNotFoundException;

    public List<String> getTel() throws SQLException, ClassNotFoundException;
}
