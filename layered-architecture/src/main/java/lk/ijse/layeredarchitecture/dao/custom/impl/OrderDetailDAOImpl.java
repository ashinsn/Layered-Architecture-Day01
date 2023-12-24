package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.dto.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(String orderId, OrderDetail entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.execute("INSERT INTO `OrderDetails` (oid, itemCode, qty, unitPrice) VALUES (?,?,?,?)", orderId, entity.getItemCode(), entity.getQty(), entity.getUnitPrice());
    }
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetail search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

}
