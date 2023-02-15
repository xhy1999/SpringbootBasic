package com.xhy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.xhy.common.*.mapper")
public class BasicApplication {

    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

}
