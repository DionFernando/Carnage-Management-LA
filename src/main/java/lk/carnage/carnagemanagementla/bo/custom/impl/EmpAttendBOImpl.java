package lk.carnage.carnagemanagementla.bo.custom.impl;

import lk.carnage.carnagemanagementla.bo.custom.EmpAttendBO;
import lk.carnage.carnagemanagementla.dao.DAOFactory;
import lk.carnage.carnagemanagementla.dao.custom.EmpAttedDAO;
import lk.carnage.carnagemanagementla.dto.EmpAttendDTO;
import lk.carnage.carnagemanagementla.entity.EmpAttend;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmpAttendBOImpl implements EmpAttendBO {

   EmpAttedDAO empAttedDAO = (EmpAttedDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEEATTENDANCE);
    @Override
    public ArrayList<EmpAttendDTO> getAllEmpAttend(String empID) throws SQLException, ClassNotFoundException {
        ArrayList<EmpAttend> empAttend = empAttedDAO.getAll();
        ArrayList<EmpAttendDTO> empAttendDTOS = new ArrayList<>();

        for (EmpAttend a : empAttend){
            EmpAttendDTO empAttendDTO = new EmpAttendDTO(
                    a.getEmpAttend_id(),
                    a.getEmp_id(),
                    a.getDate());
            empAttendDTOS.add(empAttendDTO);
        }
        return empAttendDTOS;
    }

    @Override
    public boolean addEmpAttend(EmpAttend dto) throws SQLException, ClassNotFoundException {
        return empAttedDAO.add(new EmpAttend(dto.getEmpAttend_id(),dto.getEmp_id(),dto.getDate()));
    }

    @Override
    public boolean updateEmpAttend(EmpAttendDTO dto) throws SQLException, ClassNotFoundException {
        return empAttedDAO.update(new EmpAttend(dto.getEmpAttend_id(),dto.getEmp_id(),dto.getDate()));
    }

    @Override
    public boolean deleteEmpAttend(String id) throws SQLException, ClassNotFoundException {
        return empAttedDAO.delete(id);
    }

    @Override
    public Object getCurrentEmpAttendID() throws SQLException, ClassNotFoundException {
        return empAttedDAO.getCurrentEmpAttendID();
    }

    @Override
    public Object searchByTel(String id) throws SQLException, ClassNotFoundException {
        return empAttedDAO.searchByTel();
    }
}
