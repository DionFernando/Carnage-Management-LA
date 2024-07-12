package lk.carnage.carnagemanagementla.bo.custom;

import lk.carnage.carnagemanagementla.bo.SuperBO;
import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dto.WomensDTO;
import lk.carnage.carnagemanagementla.entity.OrderDetail;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WomensBO extends SuperBO {

    public ArrayList<WomensDTO> getAllWomens() throws SQLException, ClassNotFoundException;

    public boolean addWomens(WomensDTO womensDTO) throws SQLException, ClassNotFoundException;
    public boolean updateWomens(WomensDTO womensDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteWomens(String id) throws SQLException, ClassNotFoundException;
    public String generateWomensID() throws SQLException, ClassNotFoundException;

    public List<String> getCodes() throws SQLException, ClassNotFoundException;

    public String qtyCount() throws SQLException, ClassNotFoundException;

    public Womens searchById(String code) throws SQLException, ClassNotFoundException;

    public boolean update(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;
}
