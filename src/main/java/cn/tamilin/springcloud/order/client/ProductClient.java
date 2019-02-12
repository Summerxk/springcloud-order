package cn.tamilin.springcloud.order.client;

import cn.tamilin.springcloud.order.dataobject.ProductInfo;
import cn.tamilin.springcloud.order.dto.CartDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName ProductClient
 * @Desciption 关于product的应该写在product项目里面 分模块去写 然后jar包引入到order项目里面
 * @Author summer
 * @Date 2019/2/4 12:08
 */
@FeignClient(name = "product" ,fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

	@GetMapping("/msg")
	String msg();

	@PostMapping("/listForOrder")
	List<ProductInfo> listForOrder(List<String> productList);

	@PostMapping("/decreaseStock")
	void decreaseStock(@RequestBody List<CartDTO> cartDTOList);


	/**
	 * 在order服务中调用product服务的时候 如果product服务没有启动的话 调用url会用到下面的这个方法 返回null
	 */
	@Component
	static class ProductClientFallback  implements ProductClient {
		@Override
		public String msg() {
			return null;
		}

		@Override
		public List<ProductInfo> listForOrder(List<String> productList) {
			return null;
		}

		@Override
		public void decreaseStock(List<CartDTO> cartDTOList) {

		}
	}
}
