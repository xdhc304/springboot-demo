package com.xdhc.demo.web;

import com.xdhc.demo.entity.Result;
import com.xdhc.demo.service.OrderService;
import com.xdhc.demo.service.model.OrderModel;
import com.xdhc.demo.service.model.UserModel;
import com.xdhc.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.xdhc.demo.handler.GlobalExceptionHandler.CONTENT_TYPE_FORMED;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    //封装下单请求
    @RequestMapping(value = "/createorder", method = RequestMethod.POST, consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public Result createOrder(@RequestParam(name = "itemId") Integer itemId,
                              @RequestParam(name = "promoId",required = false) Integer promoId,
                              @RequestParam(name = "amount") Integer amount) {

        //获取用户登录信息
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            return ResultUtil.error("用户还未登录，不能下单");
        }

        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, promoId, amount);

        return ResultUtil.success();
    }

}