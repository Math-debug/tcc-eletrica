package br.unip.sync.tcc;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.unip.sync.tcc.service.SyncService;

@SpringBootApplication
@EntityScan("br.unip.tcc.entity")
@EnableJpaRepositories(basePackages = {"br.unip.tcc.repository"})
@EnableScheduling
public class UnipSyncApplication {
	
	@Autowired
	SyncService syncService;

	public static void main(String[] args) {
		SpringApplication.run(UnipSyncApplication.class, args);
	}
	
	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	}
}
