package lk.carnage.carnagemanagementla.bo.custom;

import lk.carnage.carnagemanagementla.bo.SuperBO;
import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dto.MensDTO;
import lk.carnage.carnagemanagementla.entity.Mens;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MensBO extends SuperBO {
    public ArrayList<MensDTO> getAllMen() throws SQLException, ClassNotFoundException;
    public boolean addMen(MensDTO mens) throws SQLException, ClassNotFoundException;
    public boolean updateMen(MensDTO mens) throws SQLException, ClassNotFoundException;
    public boolean deleteMen(String id) throws SQLException, ClassNotFoundException;
    public String generateMenID() throws SQLException, ClassNotFoundException;

    public List<String> getCodes() throws SQLException, ClassNotFoundException;
}
