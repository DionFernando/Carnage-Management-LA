package lk.carnage.carnagemanagementla.bo.custom;

import lk.carnage.carnagemanagementla.bo.SuperBO;
import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dto.WomensDTO;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface WomensBO extends SuperBO {

    public ArrayList<WomensDTO> getAllWomens() throws SQLException, ClassNotFoundException;

    public boolean addWomens(WomensDTO womensDTO) throws SQLException, ClassNotFoundException;
    public boolean updateWomens(WomensDTO womensDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteWomens(String id) throws SQLException, ClassNotFoundException;
    public String generateWomensID() throws SQLException, ClassNotFoundException;
}
