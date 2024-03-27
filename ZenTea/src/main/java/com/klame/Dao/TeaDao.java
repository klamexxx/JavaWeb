package com.klame.Dao;

import com.klame.beans.Tea;

import java.util.List;

public interface TeaDao {
    //查询所有茶叶
    public List<Tea> findAllTea();
    //查询所有该类别茶叶
    public List<Tea> findByCategory(String category);
    //根据id查询茶叶
    public List<Tea> findTeaById(int teaId);
    //根据名称查询茶叶
    public List<Tea> findTeaByName(String teaName);
    //添加茶叶
    public int addTea(Tea tea);
    //修改茶叶
    public int updateTea(Tea tea);
    //删除茶叶
    public int deleteTea(int teaId);
}
