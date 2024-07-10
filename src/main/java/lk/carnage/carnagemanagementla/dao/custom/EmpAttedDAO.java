package lk.carnage.carnagemanagementla.dao.custom;

import lk.carnage.carnagemanagementla.dao.CrudDAO;
import lk.carnage.carnagemanagementla.entity.EmpAttend;

import java.sql.SQLException;

public interface EmpAttedDAO extends CrudDAO<EmpAttend> {
    public Object getCurrentEmpAttendID() throws SQLException, ClassNotFoundException;

    public Object searchByTel() throws SQLException, ClassNotFoundException;
}
