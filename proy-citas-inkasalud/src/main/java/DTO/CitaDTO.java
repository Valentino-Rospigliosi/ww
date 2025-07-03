package DTO;


import java.sql.Date;

import com.cibertec.edu.Entity.Citas;
import com.example.citas.ws.Cita;

public class CitaDTO {
    private Integer idCita;
    private Integer idPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String nombreMedico;
    private String apellidoMedico;
    private String especialidad;
    private Date fecha;
    private String motivo;
    private String estado;

    // Constructor
    public CitaDTO(Citas cita) {
        this.idCita = cita.getIdCita();
        this.idPaciente = cita.getPaciente().getIdPaciente();
        this.nombrePaciente = cita.getPaciente().getNombre();
        this.apellidoPaciente = cita.getPaciente().getApellido();
        this.nombreMedico = cita.getMedico().getNombre();
        this.apellidoMedico = cita.getMedico().getApellido();
        this.especialidad = cita.getMedico().getEspecialidad();
        this.fecha = cita.getFecha();
        this.motivo = cita.getMotivo();
        this.estado = cita.getEstado();
    }

    // Constructor para objetos obtenidos de servicios SOAP
    public CitaDTO(Cita cita) {
        this.idCita = cita.getId() != null ? cita.getId().intValue() : null;
        this.idPaciente = (int) cita.getIdPaciente();
        this.nombrePaciente = cita.getNombrePaciente();
        this.apellidoPaciente = cita.getApellidoPaciente();
        this.nombreMedico = cita.getMedico();
        this.apellidoMedico = "";
        this.especialidad = cita.getEspecialidad();
        if (cita.getFechaCita() != null) {
            this.fecha = java.sql.Date
                    .valueOf(cita.getFechaCita().toGregorianCalendar().toZonedDateTime().toLocalDate());
        }
        this.motivo = cita.getMotivo();
        this.estado = cita.getEstado();
    }

    // Getters y setters
    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPacientePaciente) {
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

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

