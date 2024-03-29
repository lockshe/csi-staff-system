package ynu.csistaffsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan("ynu.csistaffsystem.mapper")
public class CsiStaffSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsiStaffSystemApplication.class, args);
    }

}
