package cn.tamilin.springcloud.order.dataobject;

import cn.tamilin.sell.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/6 11:23
 */
@Data
public class BaseEntity {

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
