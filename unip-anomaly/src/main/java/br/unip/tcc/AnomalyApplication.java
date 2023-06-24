package br.unip.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.unip.tcc.entity")
@EnableJpaRepositories(basePackages = {"br.unip.tcc.repository"})

public class AnomalyApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnomalyApplication.class, args);
	}
}
