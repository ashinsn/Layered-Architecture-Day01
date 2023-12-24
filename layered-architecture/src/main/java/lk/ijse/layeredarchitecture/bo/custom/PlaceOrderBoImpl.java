package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.bo.PlaceOrderBo;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.dao.custom.OrderDAO;
import lk.ijse.layeredarchitecture.dao.custom.OrderDetailDAO;
import lk.ijse.layeredarchitecture.db.DBConnection;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;
import lk.ijse.layeredarchitecture.dto.entity.Customer;
import lk.ijse.layeredarchitecture.dto.entity.Item;
import lk.ijse.layeredarchitecture.dto.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class PlaceOrderBoImpl implements PlaceOrderBo {


    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO)  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        connection= DBConnection.getDbConnection().getConnection();
        //Check order id already exist or not
        boolean b1 = orderDAO.exist(orderId);
        /*if order id already exist*/
        if (b1) {
            return false;
        }
        connection.setAutoCommit(false);
        //Save the Order to the order table
        boolean b2 = orderDAO.save(new Order(orderId, orderDate, customerId));
        if (!b2) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        // add data to the Order Details table
        for (OrderDetailDTO detail : orderDetails) {
            boolean b3 = orderDetailsDAO.save(detail);
            if (!b3) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            //Search & Update Item
            Item item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
            //update item
            boolean b = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

            if (!b) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;

    }
    @Override
    public Item findItem(String code) {
        try {
            return itemDAO.search(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        //logic implementaiton
        return customerDAO.search(id);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }

    @Override
    public String generateOrderNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomersId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }

}
