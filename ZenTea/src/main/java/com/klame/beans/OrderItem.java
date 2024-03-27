package com.klame.beans;

import java.util.Set;

public class OrderItem{
    private String orderId;
    private Set<Tea> teaSet;

    public OrderItem() {

    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId='" + orderId + '\'' +
                ", teaSet=" + teaSet +
                '}';
    }

    public OrderItem(String orderId, Set<Tea> teaSet) {
        this.orderId = orderId;
        this.teaSet = teaSet;
    }

    public String getOrderId() {
        return orderId;
    }

    public Set<Tea> getTeaSet() {
        return teaSet;
    }

    public void setTeaSet(Set<Tea> teaSet) {
        this.teaSet = teaSet;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

