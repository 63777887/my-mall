package club.banyuan.zgMallMgt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        // 禁用数据源，因为使用了druid数据源，因此需要同样禁用druid数据源的自动配置
        DataSourceAutoConfiguration.class,
        // DruidDataSourceAutoConfigure.class
},scanBasePackages = "club.banyuan")
public class ZgMallMgtApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZgMallMgtApplication.class, args);
    }
}
