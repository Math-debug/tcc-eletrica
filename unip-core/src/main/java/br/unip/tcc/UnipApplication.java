package br.unip.tcc;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnipApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnipApplication.class, args);
	}
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
    }
}
