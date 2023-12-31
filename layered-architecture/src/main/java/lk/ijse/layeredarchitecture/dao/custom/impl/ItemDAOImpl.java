package lk.ijse.layeredarchitecture.dao.custom.impl;


import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.dto.entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<Item> getAllItems = new ArrayList<>();
        while (rst.next()) {
            getAllItems.add(new Item(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
        }
        return getAllItems;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        /*PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, item.getCode());
        pstm.setString(2, item.getDescription());
        pstm.setBigDecimal(3, item.getUnitPrice());
        pstm.setInt(4, item.getQtyOnHand());

        if(pstm.executeUpdate() > 0){
            return true;
        }
        return false;*/
         return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", entity.getCode(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException{
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, dto.getDescription());
        pstm.setBigDecimal(2, dto.getUnitPrice());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getCode());
        ;
        if(pstm.executeUpdate() > 0){
            return true;
        }
        return false;*/
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(), entity.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException{
        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        int isDeleted = pstm.executeUpdate();
        return isDeleted > 0;*/
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return resultSet.next();
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newOrderId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newOrderId);
        } else {
            return "I00-001";
        }
    }
        /*ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }*/


    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", code);
        if(rst.next()){
            Item item = new Item(code+"", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            return item;
            // return new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        }else{
            return null;
        }
    }
}
