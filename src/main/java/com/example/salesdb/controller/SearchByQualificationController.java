package com.example.salesdb.controller;

import com.example.salesdb.data.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchByQualificationController {
    @FXML
    private ListView<String> buyerList;
    @FXML
    private TextField qualificationField;
    private Repository repository;
    private MainController mainController;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void search(ActionEvent actionEvent) {
        String product = qualificationField.getText();
        display(repository.getSalesByProductName(product));
    }

    public void cancel(ActionEvent actionEvent) {
        mainController.closeStage();
    }
    private void display(List<String> items){
        if (items.size() == 0){
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            buyerList.setItems(noItems);
        }
        else {
            ObservableList<String> observableList = FXCollections.observableList(items);
            buyerList.setItems(observableList);
        }
        buyerList.setSelectionModel(null);
    }
}
