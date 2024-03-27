package com.klame.beans;

/**
 * CREATE TABLE `klame`.`teaOrder`  (
 *   `orderId` varchar(20) NOT NULL COMMENT '订单编号',
 * 	`consumerName` varchar(20) NOT NULL COMMENT '付款人',
 *   `consignee` varchar(255) NOT NULL COMMENT '收货人',
 *   `telNum` varchar(11) NOT NULL COMMENT '收货人的电话号码',
 *   `address` varchar(255) NOT NULL COMMENT '收货地址',
 *   `shippingTime` datetime NULL COMMENT '发货时间',
 *   `deliveryTime` datetime NULL COMMENT '预计送达时间',
 *   `deliveryStatus` varchar(10) NOT NULL DEFAULT '未送达' COMMENT '送达状态',
 *   `signatureStatus` varchar(10) NOT NULL DEFAULT '未签收' COMMENT '签收状态',
 *   `refundStatus` varchar(10) NOT NULL DEFAULT '无需退款' COMMENT '退款状态',
 *   PRIMARY KEY (`orderId`),
 *   FOREIGN KEY (`consumerName`) REFERENCES `consumer`(`consumerName`)
 * );
 */
public class Order {
	private String orderId;
	private String consumerName;
	private String consignee;
	private String telNum;
	private String address;
	private String shippingTime;
	private String deliveryTime;
	private String deliveryStatus;
	private String signatureStatus;

    public Order() {

    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", consumerName=" + consumerName +
                ", consignee='" + consignee + '\'' +
                ", telNum='" + telNum + '\'' +
                ", address='" + address + '\'' +
                ", shippingTime='" + shippingTime + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", signatureStatus='" + signatureStatus + '\'' +
                '}';
    }

    //getter and setter

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerId) {
        this.consumerName = consumerId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getSignatureStatus() {
        return signatureStatus;
    }

    public void setSignatureStatus(String signatureStatus) {
        this.signatureStatus = signatureStatus;
    }

}