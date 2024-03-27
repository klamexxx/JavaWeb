package com.klame.beans;

/**
 * CREATE TABLE `klame`.`tea`  (
 *   `teaId` int NOT NULL AUTO_INCREMENT COMMENT '茶叶id',
 *   `teaName` varchar(20) NOT NULL COMMENT '茶叶名',
 *   `teaImg` blob NOT NULL COMMENT '茶叶图片信息',
 *   `teaCategory` varchar(20) NOT NULL COMMENT '类别',
 *   `teaPrice` double NULL COMMENT '茶叶价格',
 *   `teaNum` double NOT NULL COMMENT '茶叶余量(单位:kg)',
 *   `teaProduce` varchar(40) NULL COMMENT '茶叶产地',
 *   `teaIntroduction` varchar(255) NULL COMMENT '茶叶简介',
 *   PRIMARY KEY (`teaId`)
 * );
 */
public class Tea {
    private int teaId;
    private String teaName;
    private String teaImg;
    private String teaCategory;
    private double teaPrice;
    private double teaNum;
    private String teaProduce;
    private String teaIntroduction;

    public Tea(int teaId, String teaName, double teaPrice, double teaNum) {


        this.teaId = teaId;
        this.teaName = teaName;
        this.teaPrice = teaPrice;
        this.teaNum = teaNum;
    }
    public Tea(int teaId, String teaName,String img, double teaPrice, double teaNum) {
        this.teaId = teaId;
        this.teaName = teaName;
        this.teaImg = img;
        this.teaPrice = teaPrice;
        this.teaNum = teaNum;
    }
    public Tea() {
    }
    //toString

    @Override
    public String toString() {
        return "Tea{" +
                "teaId=" + teaId +
                ", teaName='" + teaName + '\'' +
                ", teaImg='" + teaImg + '\'' +
                ", teaCategory='" + teaCategory + '\'' +
                ", teaPrice=" + teaPrice +
                ", teaNum=" + teaNum +
                ", teaProduce='" + teaProduce + '\'' +
                ", teaIntroduction='" + teaIntroduction + '\'' +
                '}';
    }


    //Getter and Setter

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

    public String getTeaCategory() {
        return teaCategory;
    }

    public void setTeaCategory(String teaCategory) {
        this.teaCategory = teaCategory;
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

    public String getTeaProduce() {
        return teaProduce;
    }

    public void setTeaProduce(String teaProduce) {
        this.teaProduce = teaProduce;
    }

    public String getTeaIntroduction() {
        return teaIntroduction;
    }

    public void setTeaIntroduction(String teaIntroduction) {
        this.teaIntroduction = teaIntroduction;
    }
}
