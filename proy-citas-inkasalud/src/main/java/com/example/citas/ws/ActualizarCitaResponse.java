package com.example.citas.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cita",
    "mensaje"
})
@XmlRootElement(name = "actualizarCitaResponse")
public class ActualizarCitaResponse {

    protected Cita cita;
    @XmlElement(required = true)
    protected String mensaje;

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita value) {
        this.cita = value;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String value) {
        this.mensaje = value;
    }

}
