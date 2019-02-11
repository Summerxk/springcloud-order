package cn.tamilin.springcloud.order.controller;

import cn.tamilin.springcloud.order.dto.OrderDTO;
import cn.tamilin.springcloud.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName SendMessageController
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/11 16:21
 */
@RestController
public class SendMessageController {

	@Autowired
	private StreamClient streamClient;

	@GetMapping("/sendMessage")
	public void process() {
		Message<String> build = MessageBuilder.withPayload("now " + new Date()).build();
		streamClient.output().send(build);
	}

	/**
	 * 发送一个对象
	 */
	@GetMapping("/sendMessageObject")
	public void processObject() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId("111");
		Message<OrderDTO> build = MessageBuilder.withPayload(orderDTO).build();
		streamClient.output().send(build);
	}
}
