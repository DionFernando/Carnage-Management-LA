package lk.carnage.carnagemanagementla.dao.custom.impl;

import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.EmpAttedDAO;
import lk.carnage.carnagemanagementla.entity.EmpAttend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpAttendDAOImpl implements EmpAttedDAO {

    @Override
    public ArrayList<EmpAttend> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmpAttend> allEmpAttend = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Emp_Attendance");
        while (rst.next()) {
            EmpAttend empAttend = new EmpAttend(rst.getString("empAttend_id"), rst.getString("emp_id"), rst.getDate("date"));
            allEmpAttend.add(empAttend);
        }
        return allEmpAttend;
    }

    @Override
    public boolean add(EmpAttend dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Emp_Attendance VALUES (?,?,?)", dto.getEmpAttend_id(), dto.getEmp_id(), dto.getDate());
    }

    @Override
    public boolean update(EmpAttend dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Emp_Attendance SET emp_id=?, date=? WHERE empAttend_id=?", dto.getEmp_id(), dto.getDate(), dto.getEmpAttend_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Emp_Attendance WHERE empAttend_id=?", id);
    }

    @Override
    public Object getCurrentEmpAttendID() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT empAttend_id FROM Emp_Attendance ORDER BY empAttend_id DESC LIMIT 1");
    }

    @Override
    public Object searchByTel() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM Employee WHERE emp_id = ?");
    }
}
