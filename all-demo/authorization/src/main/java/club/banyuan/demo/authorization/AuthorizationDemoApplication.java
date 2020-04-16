package club.banyuan.demo.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "club.banyuan.demo")
public class AuthorizationDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationDemoApplication.class, args);
    }
}
