package com.example.citas.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cita", propOrder = {
    "id",
    "idPaciente",
    "nombrePaciente",
    "apellidoPaciente",
    "especialidad",
    "medico",
    "fechaCita",
    "motivo",
    "estado"
})
public class Cita {

    protected Long id;
    protected long idPaciente;
    @XmlElement(required = true)
    protected String nombrePaciente;
    @XmlElement(required = true)
    protected String apellidoPaciente;
    @XmlElement(required = true)
    protected String especialidad;
    @XmlElement(required = true)
    protected String medico;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCita;
    @XmlElement(required = true)
    protected String motivo;
    @XmlElement(required = true)
    protected String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long value) {
        this.id = value;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String value) {
        this.nombrePaciente = value;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String value) {
        this.apellidoPaciente = value;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String value) {
        this.especialidad = value;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String value) {
        this.medico = value;
    }

    public XMLGregorianCalendar getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(XMLGregorianCalendar value) {
        this.fechaCita = value;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String value) {
        this.motivo = value;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String value) {
        this.estado = value;
    }

}
