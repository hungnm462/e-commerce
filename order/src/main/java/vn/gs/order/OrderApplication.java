package vn.gs.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.gs.order.config.env.GreenSnakeProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {GreenSnakeProperties.class})
//@EnableJpaRepositories
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
