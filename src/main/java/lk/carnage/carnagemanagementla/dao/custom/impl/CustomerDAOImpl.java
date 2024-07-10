package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.CustomerDAO;
import lk.carnage.carnagemanagementla.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("cus_id"), rst.getString("name"), rst.getInt("tel"),rst.getString("address"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (cus_id,name, address) VALUES (?,?,?)", customer.getId(), customer.getName(), customer.getTel(), customer.getAddress());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE cus_id=?", customer.getName(), customer.getAddress(), customer.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE cus_id=?", id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT cus_id FROM Customer ORDER BY cus_id DESC LIMIT 1;");
    }

    @Override
    public Customer searchByTel(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM Customer WHERE tel = ?");
    }
}
