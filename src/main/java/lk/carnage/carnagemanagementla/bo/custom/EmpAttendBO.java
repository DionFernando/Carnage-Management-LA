package lk.carnage.carnagemanagementla.bo.custom;

import lk.carnage.carnagemanagementla.bo.SuperBO;
import lk.carnage.carnagemanagementla.dto.EmpAttendDTO;
import lk.carnage.carnagemanagementla.entity.EmpAttend;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmpAttendBO extends SuperBO {
    public ArrayList<EmpAttendDTO> getAllEmpAttend(String empID) throws SQLException, ClassNotFoundException;
    public boolean addEmpAttend(EmpAttend dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmpAttend(EmpAttendDTO dto) throws SQLException, ClassNotFoundException;
    public boolean deleteEmpAttend(String id) throws SQLException, ClassNotFoundException;

    public Object getCurrentEmpAttendID() throws SQLException, ClassNotFoundException;

    public Object searchByTel(String id) throws SQLException, ClassNotFoundException;

}
