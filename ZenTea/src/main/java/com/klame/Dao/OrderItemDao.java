package com.klame.Dao;

import com.klame.beans.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderItemDao {
    public int addOrderItem(OrderItem orderItem);
    public int deleteOrderItem(String orderItemId);
    public Map<Integer,OrderItem> getAllOrderItems();
    public OrderItem getOrderItemById(String orderItemId);
}
