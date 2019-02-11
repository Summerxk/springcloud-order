package cn.tamilin.springcloud.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MqReceiver
 * @Desciption 接收mq消息
 * @Author summer
 * @Date 2019/2/11 15:22
 */
@Slf4j
@Component
public class MqReceiver {
	// 1. 需要手动在mq上添加队列
	//@RabbitListener(queues = "myQueue")

	// 2. 自动创建队列
	//@RabbitListener(queuesToDeclare = @Queue("myQueue"))

	//3. 自动创建，Exchange和Queue绑定
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue("myQueue"),
			exchange = @Exchange("myExchange")
	))
	public void processtest(String message) {
		log.info("mqReceiver:{}", message);
	}

	/**
	 * 电脑供应商的服务
	 * @param message
	 */
	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange("myOrder"),
			key = "computer", // 只接收computer类型的消息
			value = @Queue("computerOrder")
	))
	public void processComputer(String message) {
		log.info("computer mqReceiver:{}", message);
	}

	/**
	 * 水果供应商的服务
	 * @param message
	 */
	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange("myOrder"),
			key = "fruit", // 只接收fruit类型的消息
			value = @Queue("fruitOrder")
	))
	public void processFruit(String message) {
		log.info("fruit mqReceiver:{}", message);
	}
}
