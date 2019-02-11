package cn.tamilin.springcloud.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EnvController
 * @Desciption TODO
 * @Author summer
 * @Date 2019/2/7 16:10
 */
@RestController
@RequestMapping("/env")
@RefreshScope // 在测试配置文件刷新后访问的时候 要加这个配置
public class EnvController {

	@Value("${env}")
	private String env;

	@GetMapping("/print")
	public String print() {
		return env;
	}
}
