package com.sena.citaspsicologia.citas_psicologia_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sena.citaspsicologia.citas_psicologia_app")
// 1. Le decimos dónde están los Repositorios (Repository)
@EnableJpaRepositories(basePackages = "com.sena.citaspsicologia.citas_psicologia_app.repository")
// 2. Le decimos dónde están las Entidades (Modelos/Tablas)
@EntityScan(basePackages = "com.sena.citaspsicologia.citas_psicologia_app.model")
public class CitasPsicologiaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasPsicologiaAppApplication.class, args);
	}

}