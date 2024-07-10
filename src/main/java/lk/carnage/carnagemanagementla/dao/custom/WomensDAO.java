package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.Customer;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.SQLException;

public interface WomensDAO extends CrudDAO<Womens> {
    public String generateID() throws SQLException, ClassNotFoundException;

}
