package club.banyuan.demo.authorization.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("club.banyuan.demo.authorization.dao")
public class MybatisConfig {
}
