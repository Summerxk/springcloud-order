package cn.tamilin.springcloud.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName OrderForm
 * @Desciption form包，表单验证用的
 * @Author summer
 * @Date 2019/1/8 21:10
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    //.... other
}
