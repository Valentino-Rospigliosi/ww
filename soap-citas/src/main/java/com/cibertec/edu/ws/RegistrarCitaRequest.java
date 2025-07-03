package com.cibertec.edu.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegistrarCitaRequest", namespace = "http://cibertec.edu/citas")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrarCitaRequest {

    @XmlElement(required = true)
    private int idPaciente;

    @XmlElement(required = true)
    private String nombrePaciente;

    @XmlElement(required = true)
    private String apellidoPaciente;

    @XmlElement(required = true)
    private String especialidad;

    @XmlElement(required = true)
    private String medico;

    @XmlElement(required = true)
    private String fecha;

    @XmlElement(required = true)
    private String motivo;

    @XmlElement(required = true)
    private String estado;

    // Getters y Setters

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
