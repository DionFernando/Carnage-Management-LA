package lk.carnage.carnagemanagementla.bo.custom.impl;

import lk.carnage.carnagemanagementla.bo.custom.CustomerBO;
import lk.carnage.carnagemanagementla.dao.DAOFactory;
import lk.carnage.carnagemanagementla.dao.SQLUtil;
import lk.carnage.carnagemanagementla.dao.custom.CustomerDAO;
import lk.carnage.carnagemanagementla.dto.CustomerDTO;
import lk.carnage.carnagemanagementla.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer c : customers){
            CustomerDTO customerDTO = new CustomerDTO(
                    c.getId(),
                    c.getName(),
                    c.getTel(),
                    c.getAddress());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean addCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customer.getId(), customer.getName(), customer.getTel(), customer.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getTel(), customer.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);

    }
    @Override
    public String generateCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateID();
    }

    public Customer searchByTel(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.searchByTel(id);
    }

    @Override
    public List<String> getCustomerAddress() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerAddress();
    }

    @Override
    public List<String> getTel() throws SQLException, ClassNotFoundException {
        return customerDAO.getTel();
    }
}
