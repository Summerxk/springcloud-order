package cn.tamilin.springcloud.order.dto;

import cn.tamilin.springcloud.order.dataobject.BaseEntity;
import cn.tamilin.springcloud.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderDTO
 * @Desciption
 * @Author summer
 * @Date 2019/1/7 23:43
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO extends BaseEntity {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
