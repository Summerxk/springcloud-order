package cn.tamilin.springcloud.order.controller;

import cn.tamilin.springcloud.order.VO.ResultVO;
import cn.tamilin.springcloud.order.dto.OrderDTO;
import cn.tamilin.springcloud.order.exception.BusinessException;
import cn.tamilin.springcloud.order.form.OrderForm;
import cn.tamilin.springcloud.order.service.OrderService;
import cn.tamilin.springcloud.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BuyerOrderController
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/8 21:07
 */
@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new BusinessException("参数异常：" + bindingResult.getFieldError().getDefaultMessage());

        OrderDTO orderDto = new OrderDTO();
        //orderForm 转 orderDTO
        orderService.create(orderDto);
        return ResultVOUtil.success();
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        //判断传递参数
        //......

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> list = orderService.findList(openid, request);
        return ResultVOUtil.success(list.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        return ResultVOUtil.success();
    }

    //取消订单
    @PutMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        return ResultVOUtil.success();
    }
}
