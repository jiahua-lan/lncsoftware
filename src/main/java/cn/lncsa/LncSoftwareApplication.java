package cn.lncsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JiaHu
 *         2017/1/9.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class LncSoftwareApplication {
    public static void main(String[] args) {
        SpringApplication.run(LncSoftwareApplication.class, args);
    }
}
