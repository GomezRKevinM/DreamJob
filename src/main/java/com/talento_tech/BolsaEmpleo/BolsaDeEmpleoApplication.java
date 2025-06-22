package com.talento_tech.BolsaEmpleo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BolsaDeEmpleoApplication {


	public static void main(String[] args) {
		SpringApplication.run(BolsaDeEmpleoApplication.class, args);
		System.out.println("Aplicación de Bolsa de Empleo iniciada correctamente.");
		System.out.println("¡Bienvenido a la aplicación de Bolsa de Empleo!");
		System.out.println("Visita nuestro sitio web para más información: http://localhost:8080/");
		System.out.println("Para más información, visita nuestro repositorio en GitHub: https://github.com/TalentoTech/BolsaEmpleo");
	}

}
