package com.example.citas.model;

import jakarta.persistence.*; // Importa las anotaciones de JPA
import lombok.Data; // Anotación de Lombok para getters, setters, etc.
import lombok.NoArgsConstructor; // Anotación de Lombok para constructor sin argumentos
import lombok.AllArgsConstructor; // Anotación de Lombok para constructor con todos los argumentos

import java.time.LocalDate; // Para manejar fechas sin información de tiempo

@Entity // Marca esta clase como una entidad JPA
@Table(name = "citas") // Especifica el nombre de la tabla en la base de datos
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class CitaEntity {

    @Id // Marca el campo 'id' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática de IDs (autoincremental)
    private Long id;
    private Long idPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String especialidad;
    private String medico;
    private LocalDate fechaCita;
    private String motivo;
    private String estado; // Por ejemplo: Pendiente, Confirmada, Cancelada, Completada

    /**
     * Constructor personalizado para crear una nueva CitaEntity sin un ID,
     * ya que el ID será generado por la base de datos.
     * @param idPaciente ID del paciente
     * @param nombrePaciente Nombre del paciente
     * @param apellidoPaciente Apellido del paciente
     * @param especialidad Especialidad de la cita
     * @param medico Médico asignado a la cita
     * @param fechaCita Fecha de la cita
     * @param motivo Motivo de la cita
     * @param estado Estado actual de la cita
     */
    public CitaEntity(Long idPaciente, String nombrePaciente, String apellidoPaciente,
                      String especialidad, String medico, LocalDate fechaCita, String motivo, String estado) {
        this.idPaciente = idPaciente;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.especialidad = especialidad;
        this.medico = medico;
        this.fechaCita = fechaCita;
        this.motivo = motivo;
        this.estado = estado;
    }
}
