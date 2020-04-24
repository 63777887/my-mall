package club.banyuan.zgMallMgt.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement    //开启事务支持
@Configuration
@MapperScan("club.banyuan.zgMallMgt.dao")
public class MybatisConfig {
}
