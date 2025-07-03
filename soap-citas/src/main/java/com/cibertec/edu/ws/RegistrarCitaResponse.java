package com.cibertec.edu.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegistrarCitaResponse", namespace = "http://cibertec.edu/citas")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrarCitaResponse {

    @XmlElement(required = true)
    private String resultado;

    // Getter y Setter
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
