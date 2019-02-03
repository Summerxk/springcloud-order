package cn.tamilin.springcloud.order.VO;

import lombok.Data;

/**
 * @ClassName ResultVO
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/6 22:49
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg = "";

    /** 返回内容. */
    private T data;
}
