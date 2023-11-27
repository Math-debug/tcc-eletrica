package br.unip.tcc.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.unip.tcc")
public class AppConfigTest {
}
