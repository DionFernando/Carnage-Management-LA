package lk.carnage.carnagemanagementla.bo.custom;

import lk.carnage.carnagemanagementla.bo.SuperBO;
import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dto.EmployeeDTO;
import lk.carnage.carnagemanagementla.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    public Object generateID() throws SQLException, ClassNotFoundException;
}
