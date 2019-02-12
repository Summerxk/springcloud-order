package cn.tamilin.springcloud.order.service.impl;


import cn.tamilin.springcloud.order.client.ProductClient;
import cn.tamilin.springcloud.order.dataobject.OrderDetail;
import cn.tamilin.springcloud.order.dataobject.OrderMaster;
import cn.tamilin.springcloud.order.dataobject.ProductInfo;
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
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional // 在订单入库的时候 加个这个单单数据库的话 可以这样使用 但是 redis不会回滚 所以要手动回滚
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.genUniquekey();

        //TODO 1.查询商品信息 调用商品服务
        List<String> productIdList = orderDTO.getOrderDetailList()
                                            .stream()
                                            .map(OrderDetail::getProductId)
                                            .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        // 读redis
        // 减库存并将值重新设置进redis 这里用redis的分布式锁 因为涉及到多线程
        // 数据库订单入库异常 需要手动回滚redis 可以加try catche
        //TODO 2.计算订单金额
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for(ProductInfo productInfo : productInfoList) {
                if(productInfo.getProductId().equals(orderDetail.getProductId())) {
                    // 单价 * 数量
					orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
					BeanUtils.copyProperties(productInfo, orderDetail);
					orderDetail.setOrderId(orderId);
					orderDetail.setDetailId(KeyUtil.genUniquekey());
					//TODO 3.保存订单详情
					orderDetailRepository.save(orderDetail);
                }
            }
        }
		//TODO 4.下单成功扣库存
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
			new CartDTO(e.getProductId(), e.getProductQuantity())
		).collect(Collectors.toList());
		productClient.decreaseStock(cartDTOList);


        //TODO 5.保存订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCESS.getCode());
        orderMasterRepository.save(orderMaster);


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
