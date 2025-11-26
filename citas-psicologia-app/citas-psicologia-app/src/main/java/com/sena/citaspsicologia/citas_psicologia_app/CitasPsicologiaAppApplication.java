package com.sena.citaspsicologia.citas_psicologia_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Aquí le decimos: "Busca archivos en tu carpeta Y TAMBIÉN en la carpeta 'citas_psicologia_app'"
@SpringBootApplication(scanBasePackages = {"com.sena.citaspsicologia.citas_psicologia_app", "citas_psicologia_app"})
public class CitasPsicologiaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitasPsicologiaAppApplication.class, args);
    }

}
