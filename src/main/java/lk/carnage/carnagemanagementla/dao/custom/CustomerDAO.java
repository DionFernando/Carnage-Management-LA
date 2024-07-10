package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    public String generateID() throws SQLException, ClassNotFoundException;

    public Customer searchByTel(String id) throws SQLException, ClassNotFoundException;
}
