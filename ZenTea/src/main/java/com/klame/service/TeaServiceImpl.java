package com.klame.service;

import com.klame.Dao.TeaDao;
import com.klame.beans.Tea;

import java.util.List;

public class TeaServiceImpl implements TeaService{

    private TeaDao teaDao;

    public TeaServiceImpl(TeaDao teaDao) {
        this.teaDao = teaDao;
    }

    @Override
    public List<Tea> getAllTea() {
        return teaDao.findAllTea();
    }

    @Override
    public List<Tea> getTeaByCategory(String category) {
        return teaDao.findByCategory(category);
    }

    @Override
    public List<Tea> getTeaById(int teaId) {
        return teaDao.findTeaById(teaId);
    }

    @Override
    public List<Tea> getTeaByName(String teaName) {

        return teaDao.findTeaByName(teaName);
    }

    @Override
    public boolean addTea(Tea tea) {
        int result = teaDao.addTea(tea);
        return result > 0;
    }

    @Override
    public boolean updateTea(Tea tea) {
        int result = teaDao.updateTea(tea);
        return result > 0;
    }

    @Override
    public boolean deleteTea(int teaId) {
        int result = teaDao.deleteTea(teaId);
        return result > 0;
    }
}
