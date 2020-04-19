package club.banyuan.zgMallMgt.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("club.banyuan.zgMallMgt.dao")
public class MybatisConfig {
}
