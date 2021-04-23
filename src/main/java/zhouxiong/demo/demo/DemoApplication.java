package zhouxiong.demo.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* @author: zhouxiong
* @create: 2021-04-23 20:06
* */
@SpringBootApplication
@MapperScan("zhouxiong.demo.demo.domain.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
