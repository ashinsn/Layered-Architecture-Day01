package lk.ijse.layeredarchitecture.bo;

import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;
import lk.ijse.layeredarchitecture.dto.entity.Item;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBo  {

    boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
    Item findItem(String code);
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    public String generateOrderNewId() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomersId() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;


}



