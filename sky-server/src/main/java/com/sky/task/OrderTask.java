package com.sky.task;


import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时处理订单状态
 */
@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;


    /**
     * 处理超时订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")// 每五分钟触发一次
    public void processTimeoutOrder() {
        log.info("定时处理超时订单:{}", LocalDateTime.now());

        // 十五分钟未下单算超时
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        // 查找超时订单
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if (ordersList != null && ordersList.size() > 0) {
            for (Orders order : ordersList) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时未支付,自动取消");
                order.setCancelTime(LocalDateTime.now());

                orderMapper.update(order);
            }
        }

    }

    /**
     * 将长时间处于派送中的订单改为已完成
     */
    @Scheduled(cron = "0 0 4 * * ?")// 每天凌晨四点触发
    public void processDeliveryOrder() {
        log.info("定时处理派送中的订单:{}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusHours(-4);

        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList != null && ordersList.size() > 0) {
            for (Orders order : ordersList) {
                order.setStatus(Orders.COMPLETED);

                orderMapper.update(order);
            }
        }
    }
}
