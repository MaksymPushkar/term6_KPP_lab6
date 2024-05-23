package com.example.salesdb.data;

import java.util.List;

public interface Repository {
    boolean addEmployee(Employee sale);
    List<Employee> getAllEmployee();
    List<String> getSalesByBuyerCode(String buyerCode);
    List<String> getSalesByProductName(String productName);
    List<String> getPayroll();
    boolean updateSale(int id, Employee sale);
    boolean deleteEmployee(int id);
    Employee getSaleById(int id);
}
