package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.bo.CustomerBo;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {
    CustomerDAO customerDAO = (CustomerDAO)  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException,ClassNotFoundException{
        return getAllCustomer();
    }
    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

}
