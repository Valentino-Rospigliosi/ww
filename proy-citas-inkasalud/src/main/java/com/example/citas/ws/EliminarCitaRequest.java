package com.example.citas.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id"
})
@XmlRootElement(name = "eliminarCitaRequest")
public class EliminarCitaRequest {

    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

}
