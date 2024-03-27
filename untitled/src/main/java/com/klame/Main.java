package com.klame;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化输出
        String formattedTime = currentTime.format(formatter);

        System.out.println("当前时间: " + formattedTime);
    }
}