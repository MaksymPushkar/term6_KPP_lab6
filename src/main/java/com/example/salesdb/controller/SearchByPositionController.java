package com.example.salesdb.controller;

import com.example.salesdb.data.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SearchByPositionController {
    @FXML
    private ListView<String> listProducts;
    @FXML
    private TextField positionField;
    private Repository repository;
    private MainController mainController;
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void search(ActionEvent actionEvent) {
        String position = positionField.getText();
        display(repository.getSalesByBuyerCode(position));
    }

    public void cancel(ActionEvent actionEvent) {
        mainController.closeStage();
    }
    private void display(List<String> items){
        if(items.size() == 0){
            List<String> l = new ArrayList<>();
            l.add("No records!");
            ObservableList<String> noItems = FXCollections.observableList(l);
            listProducts.setItems(noItems);
        }
        else {
            ObservableList<String> list = FXCollections.observableList(items);
            listProducts.setItems(list);
        }
        listProducts.setSelectionModel(null);
    }
}
