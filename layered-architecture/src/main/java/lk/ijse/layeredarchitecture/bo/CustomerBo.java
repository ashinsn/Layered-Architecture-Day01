package lk.ijse.layeredarchitecture.bo;

import lk.ijse.layeredarchitecture.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {
    static ArrayList<CustomerDTO> getAllCustomer() throws SQLException,ClassNotFoundException;
    static boolean saveCustomer(CustomerDTO dto) throws SQLException,ClassNotFoundException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
}
