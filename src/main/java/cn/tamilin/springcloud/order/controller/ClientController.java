package cn.tamilin.springcloud.order.controller;

import cn.tamilin.springcloud.order.client.ProductClient;
import cn.tamilin.springcloud.order.dataobject.ProductInfo;
import cn.tamilin.springcloud.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ClientController
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/3 22:48
 */
@RestController
@Slf4j
public class ClientController {
/**
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/getProductMsg")
	public String getProductMsg() {
		//1. RestTemplate 第一种方式 直接使用restTemplate
//		RestTemplate restTemplate = new RestTemplate();
//		String forObject = restTemplate.getForObject("http://127.0.0.1:8081/msg", String.class);
//		log.info("product server msg is : {}", forObject);

		//2. 第二种方式 使用SpringCloud 的 loadBalancerClient 通过应用名 获取url 然后再使用restTemplate
		ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
		String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/msg");
		RestTemplate restTemplate = new RestTemplate();
		String forObject = restTemplate.getForObject(url, String.class);
		log.info("loadbalancer product server msg is : {}", forObject);


		//3. 第三种方式
		String forObject3 = restTemplate.getForObject("http://PRODUCT/msg", String.class);
		log.info("config product server msg is : {}", forObject);
		return forObject;
	}
*/

	@Autowired
	private ProductClient productClient;

	@GetMapping("/productMsg")
	public String getProductMsg() {
		String msg = productClient.msg();
		log.info("feign msg is {}", msg);
		return msg;
	}

	@GetMapping("/getProductList")
	public String listForOrder() {
		List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("1", "2","3"));
		log.info("response is {}", productInfos);
		return "ok";
	}

	@GetMapping("/decreaseStock")
	public String productDecreaseStock() {
		productClient.decreaseStock(Arrays.asList(new CartDTO("1",1)));
		return "ok";
	}

}

