package cn.tamilin.springcloud.order.message;

import cn.tamilin.springcloud.order.dataobject.ProductInfo;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductInfoReceiver
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/11 17:31
 */
@Component
public class ProductInfoReceiver {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@RabbitListener(queuesToDeclare = @Queue("productInfo"))
	public void process(String message) {
//		ProductInfo
		stringRedisTemplate.opsForValue().set("key", "value");
	}
}
