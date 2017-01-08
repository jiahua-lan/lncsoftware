package cn.lncsa.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author JiaHu
 *         2017/1/9.
 */
@Configuration
@PropertySource({"classpath:jdbc.properties"})
public class DataSourceConfiguration {

    private String username;

    @Value("#{${jdbc.username}}")
    public String getUsername() {
        return username;
    }

    private String password;

    @Value("#{${jdbc.password}}")
    public String getPassword() {
        return password;
    }

    private String url;

    @Value("#{${jdbc.url}}")
    public String getUrl() {
        return url;
    }

    private String driver;

    @Value("#{${jdbc.driver}}")
    public String getDriver() {
        return driver;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
