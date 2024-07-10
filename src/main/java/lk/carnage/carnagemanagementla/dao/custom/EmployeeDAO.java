package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    public Object generateID() throws SQLException, ClassNotFoundException;
}
