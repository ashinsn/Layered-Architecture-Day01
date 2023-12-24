package lk.ijse.layeredarchitecture.bo;

import lk.ijse.layeredarchitecture.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo {
    ArrayList<ItemDTO> getAllItems() throws SQLException,ClassNotFoundException;
    boolean saveItem(ItemDTO dto) throws SQLException,ClassNotFoundException;
    boolean updateItem(ItemDTO dto) throws SQLException,ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException,ClassNotFoundException;
    String generateNewId() throws SQLException,ClassNotFoundException;
    boolean existItem(String id) throws SQLException,ClassNotFoundException;


}
