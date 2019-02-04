package cn.tamilin.springcloud.order.dataobject;

import cn.tamilin.springcloud.order.enums.OrderStatusEnum;
import cn.tamilin.springcloud.order.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName OrderMaster
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 22:27
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster extends BaseEntity {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();
}
