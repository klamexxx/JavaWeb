package com.klame.Dao;

import com.klame.beans.Consumer;

import java.sql.SQLException;

public interface ConsumerDao {
    public int addConsumer(Consumer consumer);

    public int updateConsumer(Consumer consumer);

    public int deleteConsumer(int id);

    public String checkConsumer(String phoneNum);

    public Consumer getConsumerByName(String name) throws SQLException;
}
