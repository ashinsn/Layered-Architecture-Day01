package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    boolean save(String orderId, String orderDate, String customerId) throws ClassNotFoundException, SQLException;

}

