package com.klame.service;

import com.klame.beans.Tea;

import java.util.List;

public interface TeaService {
    List<Tea> getAllTea();
    List<Tea> getTeaByCategory(String category);
    List<Tea> getTeaById(int teaId);
    List<Tea> getTeaByName(String teaName);
    boolean addTea(Tea tea);
    boolean updateTea(Tea tea);
    boolean deleteTea(int teaId);
}
