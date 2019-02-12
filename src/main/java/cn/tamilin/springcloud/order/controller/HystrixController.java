package cn.tamilin.springcloud.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @ClassName HystrixController
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/12 14:24
 */
@RestController
@DefaultProperties(defaultFallback = "defaultfallback")
public class HystrixController {

	// com.netflix.hystrix.HystrixCommandProperties
// 1.服务降级
//	@HystrixCommand(fallbackMethod = "fallback")

// 2.设置超时时间
//	@HystrixCommand(commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") // 超时时间 还有别的配置 很多的属性
//	})

// 3.熔断
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"), 						// 设置熔断
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),			//
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),	//
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
	})
	@GetMapping("/getProductInfoList")
	public String getProductInfo() {
//		RestTemplate restTemplate = new RestTemplate();
//		// 当product 服务关掉之后就会报500 那么就可以用 hystrix 进行服务降级
//		return restTemplate.postForObject("http://127.0.0.1:8081/listForOrder", Arrays.asList("123"), String.class);

		throw new RuntimeException();//发生异常也是可以的 服务内部进行降级
	}

	private String fallback() {
		return "太拥挤了，请稍后再试试";
	}

	private String defaultfallback() {
		return "默认提示： 太拥挤了";
	}
/**
 *
 private final HystrixProperty<Integer> circuitBreakerRequestVolumeThreshold; // number of requests that must be made within a statisticalWindow before open/close decisions are made using stats
 private final HystrixProperty<Integer> circuitBreakerSleepWindowInMilliseconds; // milliseconds after tripping circuit before allowing retry
 private final HystrixProperty<Boolean> circuitBreakerEnabled; // Whether circuit breaker should be enabled.
 private final HystrixProperty<Integer> circuitBreakerErrorThresholdPercentage; // % of 'marks' that must be failed to trip the circuit

 circuitBreaker.sleepWindowInMilliseconds：
 	时间窗口，断路器确定是否需要打开统计一些请求和错误数据的时候，实际上是有个时间范围的，这个时间范围就被称为时间窗口，当断路器打开对主逻辑进行熔断之后，
 	hystrix会启动一个休眠时间窗，在这个时间窗内降级逻辑会成为临时的主逻辑，当休眠时间窗到期，断路器会进入半开状态，释放一次请求到原来的主逻辑上，此次请求正常返回
 	那么断路器将继续闭合，主逻辑恢复，如果这个请求有问题断路器将继续进入打开状态，休眠时间窗重新计时
 circuitBreaker.requestVolumeThreshold：
 	设置在滚动时间窗口中，断路器的最小请求数
 circuitBreaker.errorThresholdPercentage：
 	设置断路器打开的百分比条件 60 表示在滚动时间窗口中如果发生10次调用有7次发生异常，这个时候断路器就会设置为打开状态
 */
}
