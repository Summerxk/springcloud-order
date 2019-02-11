package cn.tamilin.springcloud.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @ClassName StreamClient
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/11 16:18
 */
@Component
public interface StreamClient {

	String INPUT = "myMessage";

	String INPUT2 = "myMessage2";

	@Input(StreamClient.INPUT)
	SubscribableChannel input();

	@Output(StreamClient.INPUT)
	MessageChannel output();

	@Input(StreamClient.INPUT2)
	SubscribableChannel input2();

	@Output(StreamClient.INPUT2)
	MessageChannel output2();
}
