package cn.tamilin.springcloud.order.enums;

import lombok.Getter;

/**
 * @ClassName OrderStatusEnum
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 22:32
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0, "新订单"),

    FINISHED(1,"完成"),

    CANCEL(2,"取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
