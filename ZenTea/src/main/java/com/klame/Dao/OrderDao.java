package com.klame.Dao;

import com.klame.beans.Cart;
import com.klame.beans.Order;

import java.util.List;

public interface OrderDao {
    public int addOrder(Order order, Cart cart);

    public int updateOrder(Order order);

    public int updateDeliveryStatus(String id);

    public int deleteOrder(Order order);

    public List<Order> getOrderById(String id);

    public List<Order> getAllOrders();

}
