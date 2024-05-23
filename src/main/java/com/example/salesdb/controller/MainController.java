package com.example.salesdb.controller;

import com.example.salesdb.PayrollApplication;
import com.example.salesdb.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView employeeList;
    private Stage newWindow;
    private Repository repository;
    private MultipleSelectionModel<Employee> selectionMode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new DataBaseRepository(
                new DataBaseConnector("Employee"));
        selectionMode = employeeList.getSelectionModel();
        updateListsView(repository.getAllEmployee());

    }
    public void updateListsView(List<Employee> sales) {
        if (sales.size() == 0) {
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            employeeList.setSelectionModel(null);
            employeeList.setItems(noItems);
        }
        else {
            ObservableList<Employee> list = FXCollections.observableList(sales);
            employeeList.setSelectionModel(selectionMode);
            employeeList.setItems(list);
        }
    }
    @FXML
    public void deleteEmployee(ActionEvent actionEvent) {
        Employee toDelete =
                (Employee) employeeList.getSelectionModel().getSelectedItem();
        repository.deleteEmployee(toDelete.getId());
        updateListsView(repository.getAllEmployee());
    }
    @FXML
    public void addEmployee(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(PayrollApplication.class.getResource("add-employee-form.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Add emloyee");
        newWindow.setScene(new Scene(root, 600, 500));
        AddController addController =
                loader.getController();
        addController.setRepository(repository);
        addController.setMainController(this);
        newWindow.show();
    }
    @FXML
    public void editEmployee(ActionEvent actionEvent) {
        Employee selectedEmployee =
                (Employee) employeeList.getSelectionModel().getSelectedItem();
        newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(PayrollApplication.class.getResource("edit-employee-form.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Edit employee");
        newWindow.setScene(new Scene(root, 600, 500));
        EditController editController =
                loader.getController();
        editController.setRepository(repository);
        editController.setEmployee(selectedEmployee);
        editController.setMainController(this);
        newWindow.show();
    }

    @FXML
    public void closeStage(){
        newWindow.close();
    }

    public void searchByCode(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(PayrollApplication.class.getResource("search-by-position-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Show list by position");
        newWindow.setScene(new Scene(root, 600, 300));
        SearchByPositionController searchByPositionController =
                loader.getController();
        searchByPositionController.setRepository(repository);
        searchByPositionController.setMainController(this);
        newWindow.show();
    }

    public void searchByProduct(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(PayrollApplication.class.getResource("search-by-qualification-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Show list by qualification");
        newWindow.setScene(new Scene(root, 600, 300));
        SearchByQualificationController searchByQualificationController =
                loader.getController();
        searchByQualificationController.setRepository(repository);
        searchByQualificationController.setMainController(this);
        newWindow.show();
    }

    public void outputPayroll(ActionEvent actionEvent) {
        newWindow = new Stage();
        FXMLLoader loader =
                new FXMLLoader(PayrollApplication.class.getResource("output-payroll-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newWindow.setTitle("Show payroll list");
        newWindow.setScene(new Scene(root, 400, 300));
        OutputPayrollController outputPayrollController =
                loader.getController();
        outputPayrollController.setRepository(repository);
        outputPayrollController.setMainController(this);
        newWindow.show();
    }
}