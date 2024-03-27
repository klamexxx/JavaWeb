package com.klame.beans;

public class CartItem extends Tea {
    private int teaId; // 商品ID
    private String teaName; // 商品名称
    private String teaImg; // 商品图片
    private double teaPrice; // 商品价格
    private double teaNum; // 商品数量

    public  CartItem() {}
    public CartItem(int teaId, String teaName, String teaImg, double teaPrice, double v) {
        this.teaId = teaId;
        this.teaName = teaName;
        this.teaImg = teaImg;
        this.teaPrice = teaPrice;
        this.teaNum = v;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                " teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaImg='" + teaImg + '\'' +
                ", teaPrice=" + teaPrice +
                ", teaNum=" + teaNum +
                '}';
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaImg() {
        return teaImg;
    }

    public void setTeaImg(String teaImg) {
        this.teaImg = teaImg;
    }

    public double getTeaPrice() {
        return teaPrice;
    }

    public void setTeaPrice(double teaPrice) {
        this.teaPrice = teaPrice;
    }

    public double getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(double teaNum) {
        this.teaNum = teaNum;
    }

    public double getTotalPrice() {
        return teaPrice * teaNum;
    }
}
