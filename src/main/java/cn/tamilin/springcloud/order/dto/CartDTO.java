package cn.tamilin.springcloud.order.dto;

import lombok.Data;

/**
 * @ClassName CatDTO
 * @Desciption 购物车
 * @Author summer
 * @Date 2019/1/8 00:26
 */
@Data
public class CartDTO {

	private String productId;

	private Integer productQuantity;

	public CartDTO() {
	}

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
