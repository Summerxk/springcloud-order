package cn.tamilin.springcloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
//@EnableCircuitBreaker
@SpringCloudApplication
@ComponentScan(basePackages = "cn.tamilin") //配置包扫描
//@EnableHystrixDashboard // hystrixdashboard 配置好依赖后要加注解
public class SpringcloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudOrderApplication.class, args);
	}

}

