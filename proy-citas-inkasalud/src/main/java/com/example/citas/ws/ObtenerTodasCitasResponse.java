package com.example.citas.ws;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerTodasCitasResponse", propOrder = {
    "citas",
    "mensaje"
})
@XmlRootElement(name = "obtenerTodasCitasResponse")
public class ObtenerTodasCitasResponse {

    protected List<Cita> citas;
    @XmlElement(required = true)
    protected String mensaje;

    public List<Cita> getCitas() {
        if (citas == null) {
            citas = new ArrayList<Cita>();
        }
        return this.citas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String value) {
        this.mensaje = value;
    }

}
