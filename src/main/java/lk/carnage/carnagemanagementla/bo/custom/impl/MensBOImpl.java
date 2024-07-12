package lk.carnage.carnagemanagementla.bo.custom.impl;

import lk.carnage.carnagemanagementla.bo.custom.MensBO;
import lk.carnage.carnagemanagementla.dao.DAOFactory;
import lk.carnage.carnagemanagementla.dao.custom.MensDAO;
import lk.carnage.carnagemanagementla.dto.MensDTO;
import lk.carnage.carnagemanagementla.dto.WomensDTO;
import lk.carnage.carnagemanagementla.entity.Mens;
import lk.carnage.carnagemanagementla.entity.Womens;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensBOImpl implements MensBO {
    MensDAO mensDAO = (MensDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MENS);
    @Override
    public ArrayList<MensDTO> getAllMen() throws SQLException, ClassNotFoundException {
        ArrayList<Mens> mens = mensDAO.getAll();
        ArrayList<MensDTO> mensDTOS = new ArrayList<>();

        for (Mens m : mens){
            MensDTO mensDTO = new MensDTO(
                    m.getId(),
                    m.getCategory(),
                    m.getPrice(),
                    m.getQty(),
                    m.getSeason());
            mensDTOS.add(mensDTO);
        }
        return mensDTOS;
    }

    @Override
    public boolean addMen(MensDTO mens) throws SQLException, ClassNotFoundException {
        return mensDAO.add(new Mens(mens.getId(),mens.getCategory(),mens.getPrice(),mens.getQty(),mens.getSeason()));
    }

    @Override
    public boolean updateMen(MensDTO mens) throws SQLException, ClassNotFoundException {
        return mensDAO.update(new Mens(mens.getId(),mens.getCategory(),mens.getPrice(),mens.getQty(),mens.getSeason()));
    }

    @Override
    public boolean deleteMen(String id) throws SQLException, ClassNotFoundException {
        return mensDAO.delete(id);
    }

    @Override
    public String generateMenID() throws SQLException, ClassNotFoundException {
        return mensDAO.generateID();
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return mensDAO.getCodes();
    }
}
