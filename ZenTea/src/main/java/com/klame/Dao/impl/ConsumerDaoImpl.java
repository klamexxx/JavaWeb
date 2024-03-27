package com.klame.Dao.impl;

import com.klame.Dao.ConsumerDao;
import com.klame.beans.Consumer;
import com.klame.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerDaoImpl implements ConsumerDao {
    @Override
    public int addConsumer(Consumer consumer) {
        Connection connection = null;
        int i = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO consumer (consumerName, consumerPwd, consumerTelNum) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, consumer.getConsumerName());
                preparedStatement.setString(2, consumer.getConsumerPwd());
                preparedStatement.setString(3, consumer.getConsumerTelNum());
                i = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }


    @Override
    public int updateConsumer(Consumer consumer) {
        Connection connection = null;
        int i = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "UPDATE consumer SET consumerName=?, consumerPwd=?, consumerImg=?, consumerTelNum=?, consumerAddress=?, mail=?, perIntroduction=? WHERE consumerId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, consumer.getConsumerName());
                preparedStatement.setString(2, consumer.getConsumerPwd());
                preparedStatement.setString(4, consumer.getConsumerTelNum());
                preparedStatement.setString(5, consumer.getConsumerAddress());
                preparedStatement.setString(6, consumer.getMail());
                preparedStatement.setString(7, consumer.getPerIntroduction());
                preparedStatement.setInt(8, consumer.getConsumerId());

                i = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public int deleteConsumer(int id) {
        Connection connection = null;
        int i = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "DELETE FROM your_table_name WHERE consumerId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                i = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public String checkConsumer(String id) {
        Connection connection = null;
        String pwd = " ";
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT consumerPwd FROM consumer WHERE consumerId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    pwd = resultSet.getString("consumerPwd");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pwd;
    }

    @Override
    public Consumer getConsumerByName(String name) throws SQLException {
        Consumer consumer = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM consumer WHERE consumerName=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    consumer = new Consumer();
                    consumer.setConsumerId(resultSet.getInt("consumerId"));
                    consumer.setConsumerName(resultSet.getString("consumerName"));
                    consumer.setConsumerTelNum(resultSet.getString("consumerTelNum"));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return consumer;
    }

    public String checkConsumer2(String tel) {
        Connection connection = null;
        String pwd = " ";
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT consumerPwd FROM consumer WHERE consumerTelNum=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, tel);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    pwd = resultSet.getString("consumerPwd");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pwd;
    }

    public String checkId(String consumerId) {
        Connection connection = null;
        String id = " ";
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT consumerId FROM consumer WHERE consumerId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, consumerId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getString("consumerId");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public String checkTel(String consumerTel) {
        Connection connection = null;
        String tel = " ";
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT consumerTelNum FROM consumer WHERE consumerTelNum=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, consumerTel);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tel = resultSet.getString("consumerTelNum");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tel;
    }

}

