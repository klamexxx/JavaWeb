package com.klame.service;

import com.klame.beans.Cart;
import com.klame.beans.Order;
import com.klame.beans.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public boolean addOrderItem(OrderItem orderItem);

    public boolean deleteOrderItem(String orderItemId);

    public Map<Integer, OrderItem> getAllOrderItems();

    public OrderItem getOrderItemById(String orderItemId);

    public boolean addOrder(Order order, Cart cart);

    public boolean updateOrder(Order order);

    public boolean updateDeliveryStatus(String id);

    public boolean deleteOrder(Order order);

    public List<Order> getOrderById(String id);

    public List<Order> getAllOrders();

}
