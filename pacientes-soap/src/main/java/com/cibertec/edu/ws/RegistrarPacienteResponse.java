package com.cibertec.edu.ws;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "RegistrarPacienteResponse", namespace = "http://cibertec.edu/pacientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrarPacienteResponse {

    @XmlElement(required = true)
    private String resultado;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
