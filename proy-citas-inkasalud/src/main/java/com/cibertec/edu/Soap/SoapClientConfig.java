package com.cibertec.edu.Soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller citasMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.citas.ws");
        return marshaller;
    }

    @Bean
    public SoapCitasClient citasClient(Jaxb2Marshaller citasMarshaller) {
        SoapCitasClient client = new SoapCitasClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(citasMarshaller);
        client.setUnmarshaller(citasMarshaller);
        return client;
    }
}
