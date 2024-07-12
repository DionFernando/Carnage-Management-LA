package lk.carnage.carnagemanagementla.bo.custom.impl;

import lk.carnage.carnagemanagementla.bo.custom.WomensBO;
import lk.carnage.carnagemanagementla.dao.DAOFactory;
import lk.carnage.carnagemanagementla.dao.custom.WomensDAO;
import lk.carnage.carnagemanagementla.dao.custom.impl.WomensDAOImpl;
import lk.carnage.carnagemanagementla.dto.CustomerDTO;
import lk.carnage.carnagemanagementla.dto.WomensDTO;
import lk.carnage.carnagemanagementla.entity.Customer;
import lk.carnage.carnagemanagementla.entity.OrderDetail;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WomensBOImpl implements WomensBO {

    WomensDAO womensDAO = (WomensDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WOMENS);
    @Override
    public ArrayList<WomensDTO> getAllWomens() throws SQLException, ClassNotFoundException {
        ArrayList<Womens> womens = womensDAO.getAll();
        ArrayList<WomensDTO> womensDTOS = new ArrayList<>();

        for (Womens w : womens){
            WomensDTO womensDTO = new WomensDTO(
                    w.getId(),
                    w.getCategory(),
                    w.getPrice(),
                    w.getQty(),
                    w.getSeason());
            womensDTOS.add(womensDTO);
        }
        return womensDTOS;
    }
    @Override
    public boolean addWomens(WomensDTO womensDTO) throws SQLException, ClassNotFoundException {
        return womensDAO.add(new Womens(womensDTO.getId(),womensDTO.getCategory(),womensDTO.getPrice(),womensDTO.getQty(),womensDTO.getSeason()));
    }

    @Override
    public boolean updateWomens(WomensDTO womensDTO) throws SQLException, ClassNotFoundException {
        return womensDAO.update(new Womens(womensDTO.getId(),womensDTO.getCategory(),womensDTO.getPrice(),womensDTO.getQty(),womensDTO.getSeason()));
    }

    @Override
    public boolean deleteWomens(String id) throws SQLException, ClassNotFoundException {
        return womensDAO.delete(id);
    }

    @Override
    public String generateWomensID() throws SQLException, ClassNotFoundException {
        return womensDAO.generateID();
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return womensDAO.getCodes();
    }

    @Override
    public String qtyCount() throws SQLException, ClassNotFoundException {
        return womensDAO.qtyCount();
    }

    @Override
    public Womens searchById(String code) throws SQLException, ClassNotFoundException {
        return womensDAO.searchById(code);
    }

    @Override
    public boolean update(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        return womensDAO.update(odList);
    }
}
