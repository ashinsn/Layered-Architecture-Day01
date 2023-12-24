package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.bo.ItemBo;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    ItemDAO itemDAO = (ItemDAO)  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException,ClassNotFoundException{
        return getAllItems();
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

}
