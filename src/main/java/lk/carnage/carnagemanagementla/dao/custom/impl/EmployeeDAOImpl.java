package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.EmployeeDAO;
import lk.carnage.carnagemanagementla.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString("emp_id"), rst.getString("name"),rst.getInt("Telephone"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean add(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee VALUES (?,?,?)", dto.getId(), dto.getName(), dto.getTel());
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET name = ?, tel = ? WHERE emp_id = ?", dto.getName(), dto.getTel(), dto.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE emp_id=?", id);
    }

    @Override
    public Object generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT emp_id FROM Employee ORDER BY emp_id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("emp_id");
        } else {
            return null;
        }
    }
}
