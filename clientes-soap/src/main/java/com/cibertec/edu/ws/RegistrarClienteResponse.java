package com.cibertec.edu.ws;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "RegistrarClienteResponse", namespace = "http://cibertec.edu/clientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrarClienteResponse {

    @XmlElement(required = true)
    private String resultado;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
