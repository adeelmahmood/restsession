package restsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableRedisHttpSession
public class Application {

	@Bean
	public JedisConnectionFactory connectionFactory() throws Exception {
		return new JedisConnectionFactory(); 
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
