package cn.tamilin.springcloud.order.service.impl;


import cn.tamilin.springcloud.order.dataobject.OrderDetail;
import cn.tamilin.springcloud.order.dataobject.OrderMaster;
import cn.tamilin.springcloud.order.dto.CartDTO;
import cn.tamilin.springcloud.order.dto.OrderDTO;
import cn.tamilin.springcloud.order.enums.OrderStatusEnum;
import cn.tamilin.springcloud.order.enums.PayStatusEnum;
import cn.tamilin.springcloud.order.exception.BusinessException;
import cn.tamilin.springcloud.order.repository.OrderDetailRepository;
import cn.tamilin.springcloud.order.repository.OrderMasterRepository;
import cn.tamilin.springcloud.order.service.OrderService;
import cn.tamilin.springcloud.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 23:49
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.genUniquekey();

        //TODO 1.查询商品信息 调用商品服务
        //TODO 2.计算订单金额
        //TODO 3.保存订单详情

        //4.保存订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCESS.getCode());
        orderMasterRepository.save(orderMaster);

        //TODO 5.下单成功扣库存
        return orderDTO;
    }

    @Override
    public OrderDTO getOne(String orderId) {
        OrderMaster ordermaster = orderMasterRepository.getOne(orderId);
        if(ordermaster == null)
            throw new BusinessException("订单不存在");
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(ordermaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetailList))
            throw new BusinessException("订单详情为空");
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(ordermaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //然后转一下
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        //修改订单状态
        //返还库存
        //如果已经支付 ，需要退款 调用payservice 的refund方法进行微信退款

        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        //修改订单状态
        return null;
    }

    @Override//支付订单
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        //判断支付状态
        //修改支付状态
        return null;
    }
}
