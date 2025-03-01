package com.sky.controller.user;


import com.sky.entity.Orders;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制类
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "订单控制类")
public class OrderController {

    public Result save(Orders orders) {
        return null;
    }
}
