package lk.ijse.layeredarchitecture.dao.custom.impl;

import lk.ijse.layeredarchitecture.dao.SQLUtil;
import lk.ijse.layeredarchitecture.dao.custom.QueryDAO;
import lk.ijse.layeredarchitecture.dto.SearchDTO;
import lk.ijse.layeredarchitecture.dto.SearchOrderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    public ArrayList<SearchDTO> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select c.name, o.oid, o.date from Customer c join Orders o on c.id = o.customerID where o.customerID = ?",id);

        ArrayList<SearchDTO> getAllsearch = new ArrayList<>();

        while (rst.next()) {
            SearchDTO searchDto = new SearchDTO(rst.getString(1), rst.getString(2), rst.getString(3));
            getAllsearch.add(searchDto);
        }

        return getAllsearch;
    }

    public ArrayList<SearchOrderDetailsDTO> addtbl(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select od.oid, o.date, od.itemCode, i.description, od.qty, od.unitPrice from OrderDetails od join Item i on od.itemCode = i.code join Orders o on od.oid = o.oid where o.oid = ?",id);

        ArrayList<SearchOrderDetailsDTO> getAlldetails = new ArrayList<>();

        while (rst.next()) {
            SearchOrderDetailsDTO searchOrderDetailsDTO = new SearchOrderDetailsDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
            getAlldetails.add(searchOrderDetailsDTO);
        }

        return getAlldetails;
    }

}
