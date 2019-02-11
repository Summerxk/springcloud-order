package cn.tamilin.springcloud.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @ClassName MqSenderTest
 * @Desciption 发送mq消息
 * @Author summer
 * @Date 2019/2/11 15:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSenderTest {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Test
	public void send() {
		amqpTemplate.convertAndSend("myQueue", "now " + new Date());
	}


	@Test
	public void sendOrder() {
		amqpTemplate.convertAndSend("myOrder","computer", "now " + new Date());
		amqpTemplate.convertAndSend("myOrder","fruit", "now " + new Date());
	}
}
