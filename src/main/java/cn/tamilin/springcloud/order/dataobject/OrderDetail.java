package cn.tamilin.springcloud.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName OrderDetail
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 23:26
 */
@Entity
@Data
public class OrderDetail extends BaseEntity {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private Integer productIcon;
}
