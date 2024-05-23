package com.example.salesdb.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseRepositoryTest {
    @Test
    void addSale() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test1"));
        repository.addEmployee(new Employee("d1", "s1", "p1", "q1", 23.75, 5));
        repository.addEmployee(new Employee("d2", "s2", "p2", "q2", 22.5, 2));
        assertNotNull(repository.getSaleById(1));
    }

    @Test
    void getAllSales() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test1"));
        assertEquals(repository.getAllEmployee().size(), 2);
    }

    @Test
    void getSalesByBuyerCode() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test1"));
        assertEquals(2, repository.getSalesByBuyerCode("b2").size());
    }

    @Test
    void getSalesByProductName() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test1"));
        assertEquals(1, repository.getSalesByProductName("p1").size());
    }

    @Test
    void deleteSale() {
        DataBaseRepository repository = new DataBaseRepository(
                new DataBaseConnector("test1"));
        repository.deleteEmployee(1);
        repository.deleteEmployee(2);
        assertEquals(0, repository.getAllEmployee().size());
    }
}