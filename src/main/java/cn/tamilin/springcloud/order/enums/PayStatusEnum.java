package cn.tamilin.springcloud.order.enums;

import lombok.Getter;

/**
 * @ClassName PayStatusEnum
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 22:32
 */
@Getter
public enum PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),

    SUCESS(1,"支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
