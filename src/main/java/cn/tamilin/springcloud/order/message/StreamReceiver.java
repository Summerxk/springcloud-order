package cn.tamilin.springcloud.order.message;

import cn.tamilin.springcloud.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @ClassName StreamReceiver
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/11 16:19
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

	@StreamListener(StreamClient.INPUT)
	public void process(Object message) {
		log.info("StreamReceive:{}", message);
	}

	/**
	 * 接收orderDTO对象
	 * @param message
	 */
	@StreamListener(StreamClient.INPUT)
	@SendTo(StreamClient.INPUT2) // 这里是处理完消息之后 再发送到哪里
	public String processObject(OrderDTO message) {
		log.info("StreamReceive:{}", message);
		return "received.";
	}

	/**
	 * 上面的sendto 是往这里面发送
	 * @param message
	 */
	@StreamListener(StreamClient.INPUT2)
	public void process2(String message) {
		log.info("StreamReceive:{}", message);
	}
}
