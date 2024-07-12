package lk.carnage.carnagemanagementla.bo.custom;

import lk.carnage.carnagemanagementla.bo.SuperBO;
import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dto.CustomerDTO;
import lk.carnage.carnagemanagementla.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public boolean addCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateCustomerID() throws SQLException, ClassNotFoundException;

    public Customer searchByTel(String id) throws SQLException, ClassNotFoundException;

    public List<String> getCustomerAddress() throws SQLException, ClassNotFoundException;

    public List<String> getTel() throws SQLException, ClassNotFoundException;
}
