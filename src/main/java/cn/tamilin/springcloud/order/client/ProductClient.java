package cn.tamilin.springcloud.order.client;

import cn.tamilin.springcloud.order.dataobject.ProductInfo;
import cn.tamilin.springcloud.order.dto.CartDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName ProductClient
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/4 12:08
 */
@FeignClient(name = "product")
public interface ProductClient {

	@GetMapping("/msg")
	String msg();

	@PostMapping("/listForOrder")
	List<ProductInfo> listForOrder(List<String> productList);

	@PostMapping("/decreaseStock")
	void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
