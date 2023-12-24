package lk.ijse.layeredarchitecture.controller;

import lk.ijse.layeredarchitecture.dao.custom.QueryDAO;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.layeredarchitecture.dao.custom.impl.QueryDAOImpl;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.SearchDTO;
import lk.ijse.layeredarchitecture.dto.SearchOrderDetailsDTO;
import lk.ijse.layeredarchitecture.view.tdm.SearchOrderTM;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;



public class SearchOrderFormController {
    public AnchorPane root;
    public TextField txtCustomerName;
    public JFXComboBox cmbOrderId;
    public TableView<SearchOrderTM> tblOrderDetails;
    public Label lblId;
    public Label lblDate;
    public TextField txtOrderDate;
    public JFXComboBox cmbCustomerId;

    QueryDAOImpl queryDAO = new QueryDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllCustomers();
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = customerDAO.getAll();
        for (CustomerDTO c : allCustomers) {
            cmbCustomerId.getItems().add(c.getId());
        }

    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void OrderIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbOrderId.getValue();

        try {

            tblOrderDetails.getItems().clear();
            ArrayList<SearchOrderDetailsDTO> dtolist = QueryDAO.addtbl(id);

            for (SearchOrderDetailsDTO c : dtolist) {
                lblId.setText(c.getOid());
                txtOrderDate.setText(c.getDate());
                lblDate.setText(c.getDate());

                double total = Double.parseDouble(c.getQty()) * Double.parseDouble(c.getUnitprice());

                tblOrderDetails.getItems().add(new SearchOrderTM(c.getItemcode(), c.getDescription(), c.getQty(), c.getUnitprice(), total));
            }

            tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
            tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
            tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cusIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomerId.getValue();
        cmbOrderId.getItems().clear();
        txtOrderDate.clear();

        try {
            ArrayList<SearchDTO> dtolist = queryDAO.search(id);

            for (SearchDTO c : dtolist) {
                cmbOrderId.getItems().add(c.getOid());
                txtCustomerName.setText(c.getName());
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}