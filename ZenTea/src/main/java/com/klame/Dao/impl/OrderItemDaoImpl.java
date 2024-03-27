package com.klame.Dao.impl;

import com.klame.Dao.OrderItemDao;
import com.klame.beans.OrderItem;
import com.klame.beans.Tea;
import com.klame.utils.DBUtil;

import java.sql.*;
import java.util.*;

public class OrderItemDaoImpl implements OrderItemDao {

    @Override
    public int addOrderItem(OrderItem orderItem) {
        Connection connection = null;
        int result = 0;
        System.out.println(orderItem.getOrderId()+"开始执行");

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            // SQL插入语句
            String sql = "INSERT INTO orderitem (orderId, teaId, teaName, teaImg, teaPrice, num) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // 遍历订单项中的茶叶集合
                for (Tea tea : orderItem.getTeaSet()) {
                    // 设置插入参数
                    preparedStatement.setString(1, orderItem.getOrderId());
                    preparedStatement.setInt(2, tea.getTeaId());
                    preparedStatement.setString(3, tea.getTeaName());
                    preparedStatement.setString(4, tea.getTeaImg());
                    preparedStatement.setDouble(5, tea.getTeaPrice());
                    preparedStatement.setDouble(6, tea.getTeaNum());
                    // 执行插入操作
                    result += preparedStatement.executeUpdate();
                }
                // 提交事务
                connection.commit();
            }
        } catch (SQLException e) {
            // 回滚事务
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

        return result;
    }

    @Override
    public int deleteOrderItem(String orderId) {
        Connection connection = null;
        int result = 0;

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            // SQL删除语句
            String sql = "DELETE FROM orderitem WHERE orderId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, orderId);
                // 执行删除操作
                result = preparedStatement.executeUpdate();
                // 提交事务
                connection.commit();
            }
        } catch (SQLException e) {
            // 回滚事务
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

        return result;
    }

    @Override
    public Map<Integer, OrderItem> getAllOrderItems() {
        Connection connection = null;
        Map<Integer, OrderItem> orderItemMap = new HashMap<>();

        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();
            // SQL查询语句
            String sql = "SELECT * FROM orderitem";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String orderId = resultSet.getString("orderId");
                    int teaId = resultSet.getInt("teaId");
                    String teaName = resultSet.getString("teaName");
                    double teaPrice = resultSet.getDouble("teaPrice");
                    double num = resultSet.getDouble("num");

                    if (orderItemMap.containsKey(orderId)) {
                        OrderItem orderItem = orderItemMap.get(orderId);
                        orderItem.getTeaSet().add(new Tea(teaId, teaName, teaPrice, num));
                    } else {
                        OrderItem orderItem = new OrderItem(orderId, new HashSet<>());
                        orderItem.getTeaSet().add(new Tea(teaId, teaName, teaPrice, num));
                        orderItemMap.put(Integer.valueOf(orderId), orderItem);
                    }
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

        return orderItemMap;
    }

    @Override
    public OrderItem getOrderItemById(String orderId) {
        Connection connection = null;
        OrderItem orderItem=null;
        try {
            // 获取数据库连接
            connection = DBUtil.getConnection();
            // SQL查询语句
            String sql = "SELECT * FROM orderitem WHERE orderId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, orderId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    orderItem = new OrderItem(orderId, new HashSet<>());
                    while (resultSet.next()) {
                        int teaId = resultSet.getInt("teaId");
                        String teaName = resultSet.getString("teaName");
                        double teaPrice = resultSet.getDouble("teaPrice");
                        double num = resultSet.getDouble("num");
                        orderItem.getTeaSet().add(new Tea(teaId, teaName, teaPrice, num));
                    }
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

        return orderItem;
    }
}

