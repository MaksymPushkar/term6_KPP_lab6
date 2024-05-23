package com.example.salesdb.controller;

import com.example.salesdb.data.Employee;
import com.example.salesdb.data.Repository;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class EditController {
    public TextField departmentField;
    public TextField surnameAndInitialsField;
    public TextField positionField;
    public TextField qualificationField;
    public TextField hoursWorkedNumberField;
    public TextField hourPayField;
    private Repository repository;
    private MainController mainController;
    private Employee employee;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        departmentField.setText(employee.getDepartment());
        surnameAndInitialsField.setText(employee.getSurnameAndInitials());
        positionField.setText(employee.getPosition());
        qualificationField.setText(employee.getQualification());
        hoursWorkedNumberField.setText(Double.toString(employee.getHoursWorkedNumber()));
        hourPayField.setText(Double.toString(employee.getHourPay()));
    }
    public void saveChanges(ActionEvent actionEvent) {
        String department = departmentField.getText();
        String surnameAndInitials = surnameAndInitialsField.getText();
        String position = positionField.getText();
        String qualification = qualificationField.getText();
        double hoursWorkedNumber = Double.parseDouble(hoursWorkedNumberField.getText());
        double hourPay = Double.parseDouble(hourPayField.getText());
        employee.setDepartment(department);
        employee.setSurnameAndInitials(surnameAndInitials);
        employee.setPosition(position);
        employee.setQualification(qualification);
        employee.setHoursWorkedNumber(hoursWorkedNumber);
        employee.setHourPay(hourPay);
        repository.updateSale(employee.getId(), employee);
        mainController.closeStage();
        mainController.updateListsView(repository.getAllEmployee());
    }

    public void cancel(ActionEvent actionEvent) {
        mainController.closeStage();
    }
}
