package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<ItemDTO> getAllItems = new ArrayList<>();
        while (rst.next()) {
            getAllItems.add(new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
        }
        return getAllItems;
    }

    @Override
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, item.getCode());
        pstm.setString(2, item.getDescription());
        pstm.setBigDecimal(3, item.getUnitPrice());
        pstm.setInt(4, item.getQtyOnHand());

        if(pstm.executeUpdate() > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, dto.getDescription());
        pstm.setBigDecimal(2, dto.getUnitPrice());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setString(4, dto.getCode());
        ;
        if(pstm.executeUpdate() > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        int isDeleted = pstm.executeUpdate();
        return isDeleted > 0;
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }

    @Override
    public String genarateNewId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
}
