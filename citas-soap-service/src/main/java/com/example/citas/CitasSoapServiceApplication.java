package com.example.citas;

import org.springframework.boot.SpringApplication; // Clase principal para arrancar una aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Anotación para configurar y arrancar la aplicación

@SpringBootApplication // Anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan
public class CitasSoapServiceApplication {

    public static void main(String[] args) {
        // Método principal que ejecuta la aplicación Spring Boot
        SpringApplication.run(CitasSoapServiceApplication.class, args);
    }
}
