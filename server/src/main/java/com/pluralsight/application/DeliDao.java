package com.pluralsight.application;

import java.util.List;

public interface DeliDao {
    List<Deli> getAllDelis();
    Deli getDeliById(int id);
    void addDeli(Deli deli);
    void updateDeli(Deli deli);
    void deleteDeli(int id);
}