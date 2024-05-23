package com.example.salesdb.controller;

import com.example.salesdb.data.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OutputPayrollController implements Initializable {
    @FXML
    private ListView<String> listPayroll;
    private Repository repository;
    private MainController mainController;

    public void setRepository(Repository repository) {
        this.repository = repository;
        display(repository.getPayroll()); // Populate the list immediately after setting the repository
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void close() {
        mainController.closeStage();
    }

    private void display(List<String> items) {
        if (items.size() == 0) {
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            listPayroll.setItems(noItems);
        } else {
            ObservableList<String> list = FXCollections.observableList(items);
            listPayroll.setItems(list);
        }
        listPayroll.setSelectionModel(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize method to load data immediately if repository is already set
        if (repository != null) {
            display(repository.getPayroll());
        }
    }
}
