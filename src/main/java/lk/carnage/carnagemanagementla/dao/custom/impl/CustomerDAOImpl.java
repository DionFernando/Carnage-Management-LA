package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.CustomerDAO;
import lk.carnage.carnagemanagementla.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        ResultSet rst = SQLUtil.execute("SELECT cus_id FROM Customer ORDER BY cus_id DESC LIMIT 1;");
        if (rst.next()) {
            return rst.getString("cus_id");
        } else {
            return null;
        }
    }

    @Override
    public Customer searchByTel(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE tel = ?", id);
        if (resultSet.next()) {
            return new Customer(resultSet.getString("cus_id"), resultSet.getString("name"), resultSet.getInt("tel"), resultSet.getString("address"));
        }
        return null;
    }

    @Override
    public List<String> getCustomerAddress() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT address FROM Customer");
        List<String> customerList = new ArrayList<>();
        while (resultSet.next()) {
            String address = resultSet.getString(1);
            customerList.add(address);
        }
        return customerList;
    }

    @Override
    public List<String> getTel() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT tel FROM Customer");
        List<String> telList = new ArrayList<>();
        while (rst.next()) {
            telList.add(rst.getString(1));
        }
        return telList;
    }

}
