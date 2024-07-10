package lk.carnage.carnagemanagementla.bo.custom.impl;

import lk.carnage.carnagemanagementla.bo.custom.EmployeeBO;
import lk.carnage.carnagemanagementla.dao.DAOFactory;
import lk.carnage.carnagemanagementla.dao.custom.EmployeeDAO;
import lk.carnage.carnagemanagementla.dto.CustomerDTO;
import lk.carnage.carnagemanagementla.dto.EmployeeDTO;
import lk.carnage.carnagemanagementla.entity.Customer;
import lk.carnage.carnagemanagementla.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee e : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    e.getId(),
                    e.getName(),
                    e.getTel());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getId(), dto.getName(), dto.getTel()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getId(), dto.getName(), dto.getTel()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public Object generateID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateID();
    }
}
