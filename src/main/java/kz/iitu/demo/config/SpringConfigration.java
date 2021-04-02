package kz.iitu.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("kz.iitu.demo")
@PropertySource("classpath:application.properties")
public class SpringConfigration {


}
