package cn.lncsa.configuration;

import org.pegdown.PegDownProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author JiaHu
 * 2017/1/9.
 */
@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public PegDownProcessor pegDownProcessor(){
        return new PegDownProcessor();
    }
}
