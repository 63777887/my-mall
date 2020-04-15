package club.banyuan.demo.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "club.banyuan.demo")
public class AuthenticationDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationDemoApplication.class, args);
    }
}
