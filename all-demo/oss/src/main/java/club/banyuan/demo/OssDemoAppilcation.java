package club.banyuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "club.banyuan.demo")
public class OssDemoAppilcation {

    public static void main(String[] args) {
        SpringApplication.run(OssDemoAppilcation.class, args);
    }
}
