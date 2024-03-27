package com.klame.beans;

import java.util.Arrays;

/**
 * CREATE TABLE `klame`.`consumer`  (
 *   `consumerId` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
 *   `consumerName` varchar(20) NOT NULL COMMENT '用户名',
 *   `consumerPwd` varchar(28) NOT NULL CHECK (LENGTH(consumerPwd) >= 6) COMMENT '用户密码长度大于等于6位',
 *   `consumerImg` blob NOT NULL COMMENT '头像信息',
 *   `consumerTelNum` varchar(11) NOT NULL COMMENT '用户的电话号码',
 *   `consumerAddress` varchar(40) NULL COMMENT '用户居住地',
 *   `mail` varchar(40) NULL COMMENT '邮箱',
 *   `perIntroduction` varchar(255) NULL COMMENT '个人简介',
 *   PRIMARY KEY (`consumerId`),
 *   UNIQUE KEY (`consumerName`)
 * )
 */
public class Consumer {
    private Integer consumerId;
    private String consumerName;
    private String consumerPwd;
    private String consumerTelNum;
    private String consumerAddress;
    private String mail;
    private String perIntroduction;

    public Consumer() {

    }
    //toString
    @Override
    public String toString() {
        return "Consumer{" +
                "consumerId=" + consumerId +
                ", consumerName='" + consumerName + '\'' +
                ", consumerPwd='" + consumerPwd + '\'' +
                ", consumerTelNum='" + consumerTelNum + '\'' +
                ", consumerAddress='" + consumerAddress + '\'' +
                ", mail='" + mail + '\'' +
                ", perIntroduction='" + perIntroduction + '\'' +
                '}';
    }

    //getter and setter
    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerPwd() {
        return consumerPwd;
    }

    public void setConsumerPwd(String consumerPwd) {
        this.consumerPwd = consumerPwd;
    }

    public String getConsumerTelNum() {
        return consumerTelNum;
    }

    public void setConsumerTelNum(String consumerTelNum) {
        this.consumerTelNum = consumerTelNum;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPerIntroduction() {
        return perIntroduction;
    }

    public void setPerIntroduction(String perIntroduction) {
        this.perIntroduction = perIntroduction;
    }


}