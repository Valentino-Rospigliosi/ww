package com.example.citas.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cita"
})
@XmlRootElement(name = "registrarCitaRequest")
public class RegistrarCitaRequest {

    @XmlElement(required = true)
    protected Cita cita;

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita value) {
        this.cita = value;
    }

}
