package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.CrudDAO;
import lk.ijse.layeredarchitecture.dto.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    boolean save(String orderId, OrderDetail entity) throws ClassNotFoundException, SQLException;

}

