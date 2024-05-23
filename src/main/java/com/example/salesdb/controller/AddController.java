package com.example.salesdb.controller;

import com.example.salesdb.data.Employee;
import com.example.salesdb.data.Repository;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddController {
    @FXML
    private TextField departmentField;
    @FXML
    private TextField surnameAndInitialsField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField qualificationField;
    @FXML
    private TextField hoursWorkedNumberField;
    @FXML
    private TextField hourPayField;
    
    private Repository repository;
    private MainController mainController;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void addEmployee() {
        // Retrieve data from the fields
        String department = departmentField.getText();
        String surnameAndInitials = surnameAndInitialsField.getText();
        String position = positionField.getText();
        String qualification = qualificationField.getText();
        double hoursWorkedNumber = Double.parseDouble(hoursWorkedNumberField.getText());
        double hourPay = Double.parseDouble(hourPayField.getText());
        Employee employee = new Employee(department, surnameAndInitials, position, qualification,
                hoursWorkedNumber, hourPay);
        repository.addEmployee(employee);
        mainController.closeStage();
        mainController.updateListsView(repository.getAllEmployee());
    }
    @FXML
    public void cancel() {
        mainController.closeStage();
    }
}