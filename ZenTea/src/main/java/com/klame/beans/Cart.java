package com.klame.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items = new HashMap<Integer, CartItem>(); // 购物车项目列表

    /**
     * 添加商品到购物车
     * @param item 商品
     */
    public void addItem(CartItem item) {
        int teaId = item.getTeaId();
        if (items.containsKey(teaId)) { // 如果购物车已经存在该商品，则将数量加1
            CartItem cartItem = items.get(teaId);
            cartItem.setTeaNum(cartItem.getTeaNum() + item.getTeaNum());
        } else { // 如果购物车中不存在该商品，则添加到购物车
            items.put(teaId, item);
        }
    }

    /**
     * 从购物车中删除商品
     * @param teaId 商品ID
     */
    public void removeItem(int teaId) {
        items.remove(teaId);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 获取购物车中的所有商品
     * @return 商品列表
     */
    public List<CartItem> getItems() {
        return new ArrayList<CartItem>(items.values());
    }

    /**
     * 获取购物车中的商品数量
     * @return 商品数量
     */
    public int getItemCount() {
        int count = 0;
        for (CartItem item : items.values()) {
            count += item.getTeaNum();
        }
        return count;
    }

    /**
     * 获取购物车中的商品总价
     * @return 商品总价
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items.values()) {
            totalPrice += item.getTeaPrice() * item.getTeaNum();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
