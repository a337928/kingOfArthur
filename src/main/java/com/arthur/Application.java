package com.arthur;

/**
 * Created by wangtao on 17/2/28.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.UnknownHostException;

// same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableTransactionManagement
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment environment = app.run(args).getEnvironment();
    }
}
