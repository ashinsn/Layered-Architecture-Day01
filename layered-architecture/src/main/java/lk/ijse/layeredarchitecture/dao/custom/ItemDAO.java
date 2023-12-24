package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {


    ArrayList<ItemDTO> getAllItems();

}
