package com.klame.Dao.impl;

import com.klame.Dao.OrderDao;
import com.klame.beans.*;
import com.klame.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDaoImpl implements OrderDao {

    @Override
    public int addOrder(Order order, Cart cart) {
        long timestamp = System.currentTimeMillis();
        Random random = new Random();
        int randomNum = random.nextInt(10000); // 生成 0 到 9999 之间的随机数
        String orderNumber = "ORD" + timestamp + randomNum;
        orderNumber = orderNumber.substring(0, 16);
        Connection connection = null;

        LocalDateTime currentTime = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化输出
        String formattedTime = currentTime.format(formatter);

        int i = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO teaorder (orderId, ConsumerName, consignee, telNum, address, shippingtime) " +
                    "VALUES (?, ?, ?, ?, ? ,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, orderNumber);
                preparedStatement.setString(2, order.getConsumerName());
                preparedStatement.setString(3, order.getConsignee());
                preparedStatement.setString(4, order.getTelNum());
                preparedStatement.setString(5, order.getAddress());
                preparedStatement.setString(6, formattedTime);
                i = preparedStatement.executeUpdate();
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderNumber); // 设置订单项的ID
            Set<Tea> teaSet = new HashSet<>(); // 创建一个茶集合
            List<CartItem> cartItems = cart.getItems(); // 获取购物车中的所有商品项
            for (CartItem cartItem : cartItems) {
                Tea tea = new Tea(cartItem.getTeaId(), cartItem.getTeaName(), cartItem.getTeaPrice(),cartItem.getTeaNum());
                teaSet.add(tea);
            }
            orderItem.setTeaSet(teaSet);
            OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
            orderItemDao.addOrderItem(orderItem);
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
    public int updateOrder(Order order) {
        Connection connection = null;
        int i = 0;

        try {
            connection = DBUtil.getConnection();
            StringBuilder sqlBuilder = new StringBuilder("UPDATE teaorder SET ");
            List<Object> parameters = new ArrayList<>();

            if (order.getConsignee() != null&&!order.getConsignee().equals("")) {
                sqlBuilder.append("consignee=?, ");
                parameters.add(order.getConsignee());
            }

            if (order.getTelNum() != null&&!order.getTelNum().equals("")) {
                sqlBuilder.append("telNum=?, ");
                parameters.add(order.getTelNum());
            }

            if (order.getAddress() != null&&!order.getAddress().equals("")) {
                sqlBuilder.append("address=?, ");
                parameters.add(order.getAddress());
            }

            if (order.getShippingTime() != null&&!order.getShippingTime().equals("")) {
                sqlBuilder.append("shippingTime=?, ");
                parameters.add(order.getShippingTime());
            }

            if (order.getDeliveryTime() != null&&!order.getDeliveryTime().equals("")) {
                sqlBuilder.append("deliveryTime=?, ");
                parameters.add(order.getDeliveryTime());
            }

            // Remove the last comma and space
            sqlBuilder.setLength(sqlBuilder.length() - 2);

            sqlBuilder.append(" WHERE orderId=?");
            parameters.add(order.getOrderId());

            String sql = sqlBuilder.toString();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int j = 0; j < parameters.size(); j++) {
                    preparedStatement.setObject(j + 1, parameters.get(j));
                }
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
    public int updateDeliveryStatus(String id) {
        Connection connection = null;
        int i = 0;

        try {
            connection = DBUtil.getConnection();
            String sql = "UPDATE teaorder SET deliveryStatus='Delivered' WHERE orderId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
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
    public int deleteOrder(Order order) {
        Connection connection = null;
        int rowsAffected = 0;

        try {
            connection = DBUtil.getConnection();
            String sql = "DELETE FROM teaorder WHERE orderId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, order.getOrderId());
                rowsAffected = preparedStatement.executeUpdate();
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
        return rowsAffected;
    }

    @Override
    public List<Order> getOrderById(String id) {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM teaorder WHERE orderId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Order order = extractOrder(resultSet);
                    orderList.add(order);
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
        return orderList;
    }
    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM teaorder";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Order order = extractOrder(resultSet);
                    orderList.add(order);
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
        return orderList;
    }

    private Order extractOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getString("orderId"));
        order.setConsumerName(resultSet.getString("consumerName"));
        order.setConsignee(resultSet.getString("consignee"));
        order.setTelNum(resultSet.getString("telNum"));
        order.setAddress(resultSet.getString("address"));
        order.setShippingTime(resultSet.getString("shippingTime"));
        order.setDeliveryTime(resultSet.getString("deliveryTime"));
        order.setDeliveryStatus(resultSet.getString("deliveryStatus"));
        return order;
    }
}

