package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.Mens;

import java.sql.SQLException;
import java.util.List;

public interface MensDAO extends CrudDAO<Mens> {
    public String generateID() throws SQLException, ClassNotFoundException;

    public List<String> getCodes() throws SQLException, ClassNotFoundException;
}
