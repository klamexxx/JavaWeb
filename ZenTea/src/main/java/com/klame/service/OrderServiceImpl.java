package com.klame.service;

import com.klame.Dao.OrderDao;
import com.klame.Dao.OrderItemDao;
import com.klame.beans.Cart;
import com.klame.beans.Order;
import com.klame.beans.OrderItem;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService{

    private OrderItemDao orderItemDao;
    private OrderDao orderDao;
    public OrderServiceImpl(OrderItemDao orderItemDao, OrderDao orderDao){
        this.orderItemDao=orderItemDao;
        this.orderDao=orderDao;
    }
    @Override
    public boolean addOrderItem(OrderItem orderItem) {
       int result=orderItemDao.addOrderItem(orderItem);
       return result>0;
    }

    @Override
    public boolean deleteOrderItem(String orderId) {
        int result=orderItemDao.deleteOrderItem(orderId);
        return result>0;
    }

    @Override
    public Map<Integer, OrderItem> getAllOrderItems() {
        return  orderItemDao.getAllOrderItems();
    }

    @Override
    public OrderItem getOrderItemById(String orderItemId) {
        return orderItemDao.getOrderItemById(orderItemId);
    }

    @Override
    public boolean addOrder(Order order, Cart cart) {
        int result=orderDao.addOrder(order,cart);
        return result>0;
    }

    @Override
    public boolean updateOrder(Order order) {
        int result=orderDao.updateOrder(order);
        return result>0;
    }

    @Override
    public boolean updateDeliveryStatus(String id) {
        int result=orderDao.updateDeliveryStatus(id);
        return result>0;
    }


    @Override
    public boolean deleteOrder(Order order) {
        int result=orderDao.deleteOrder(order);
        return result>0;
    }

    @Override
    public List<Order> getOrderById(String id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
