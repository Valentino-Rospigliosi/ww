package com.example.citas.endpoint;

import com.example.citas.model.CitaEntity; // Importa la entidad JPA
import com.example.citas.repository.CitaRepository; // Importa el repositorio JPA
import com.example.citas.ws.*; // Importa todas las clases generadas por JAXB (RegistrarCitaRequest, Cita, etc.)
import org.springframework.beans.BeanUtils; // Utilidad para copiar propiedades entre objetos
import org.springframework.beans.factory.annotation.Autowired; // Anotación para inyección de dependencias
import org.springframework.ws.server.endpoint.annotation.Endpoint; // Anotación para marcar la clase como un endpoint SOAP
import org.springframework.ws.server.endpoint.annotation.PayloadRoot; // Mapea las solicitudes XML a métodos
import org.springframework.ws.server.endpoint.annotation.RequestPayload; // Indica que el parámetro es el payload de la solicitud
import org.springframework.ws.server.endpoint.annotation.ResponsePayload; // Indica que el valor de retorno es el payload de la respuesta

import javax.xml.datatype.DatatypeConfigurationException; // Excepción para problemas de configuración de tipos de datos XML
import javax.xml.datatype.DatatypeFactory; // Fábrica para crear objetos de tipos de datos XML
import java.time.LocalDate; // Para manejar fechas en Java 8+
import java.util.List; // Para listas de objetos
import java.util.Optional; // Para manejar valores que pueden ser nulos
import java.util.stream.Collectors; // Para operaciones de stream

@Endpoint // Marca esta clase como un endpoint de servicio web SOAP
public class CitasEndpoint {

    // Define el Namespace URI del esquema XSD
    private static final String NAMESPACE_URI = "http://www.example.com/citas";

    @Autowired // Inyecta una instancia de CitaRepository
    private CitaRepository citaRepository;

    /**
     * Maneja la solicitud para registrar una nueva cita.
     * @param request La solicitud SOAP que contiene los datos de la cita a registrar.
     * @return La respuesta SOAP con la cita registrada y un mensaje.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registrarCitaRequest")
    @ResponsePayload
    public RegistrarCitaResponse registrarCita(@RequestPayload RegistrarCitaRequest request) {
        RegistrarCitaResponse response = new RegistrarCitaResponse();
        CitaEntity citaEntity = new CitaEntity();
        
        // Copia las propiedades del objeto Cita del request (generado por JAXB) a la entidad JPA
        BeanUtils.copyProperties(request.getCita(), citaEntity); 

        // Convierte la fecha de XMLGregorianCalendar (del XSD) a LocalDate (para la entidad JPA)
        if (request.getCita().getFechaCita() != null) {
            citaEntity.setFechaCita(LocalDate.of(
                    request.getCita().getFechaCita().getYear(),
                    request.getCita().getFechaCita().getMonth(),
                    request.getCita().getFechaCita().getDay()
            ));
        }

        // Establece el estado inicial "Pendiente" si no se proporciona en la solicitud
        if (citaEntity.getEstado() == null || citaEntity.getEstado().isEmpty()) {
            citaEntity.setEstado("Pendiente");
        }

        // Guarda la nueva cita en la base de datos
        CitaEntity savedCita = citaRepository.save(citaEntity);

        Cita citaWs = new Cita();
        // Copia las propiedades de la entidad JPA guardada al objeto Cita para la respuesta SOAP
        BeanUtils.copyProperties(savedCita, citaWs); 
        
        // Convierte la fecha de LocalDate (de la entidad JPA) a XMLGregorianCalendar (para la respuesta SOAP)
        if (savedCita.getFechaCita() != null) {
            try {
                citaWs.setFechaCita(DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(savedCita.getFechaCita().toString()));
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace(); // Imprime el stack trace en caso de error de configuración de fecha
                response.setMensaje("Error al procesar la fecha de la cita.");
                return response;
            }
        }

        response.setCita(citaWs);
        response.setMensaje("Cita registrada exitosamente.");
        return response;
    }

    /**
     * Maneja la solicitud para obtener una cita por su ID.
     * @param request La solicitud SOAP que contiene el ID de la cita.
     * @return La respuesta SOAP con los detalles de la cita o un mensaje de no encontrada.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "obtenerCitaRequest")
    @ResponsePayload
    public ObtenerCitaResponse obtenerCita(@RequestPayload ObtenerCitaRequest request) {
        ObtenerCitaResponse response = new ObtenerCitaResponse();
        // Busca la cita por ID en el repositorio
        Optional<CitaEntity> citaOptional = citaRepository.findById(request.getId());

        if (citaOptional.isPresent()) {
            CitaEntity citaEntity = citaOptional.get();
            Cita citaWs = new Cita();
            BeanUtils.copyProperties(citaEntity, citaWs);
            
            // Convierte LocalDate a XMLGregorianCalendar para la respuesta SOAP
            if (citaEntity.getFechaCita() != null) {
                try {
                    citaWs.setFechaCita(DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(citaEntity.getFechaCita().toString()));
                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                    response.setMensaje("Error al procesar la fecha de la cita.");
                    return response;
                }
            }
            response.setCita(citaWs);
            response.setMensaje("Cita encontrada.");
        } else {
            response.setMensaje("Cita no encontrada para el ID: " + request.getId());
        }
        return response;
    }

    /**
     * Maneja la solicitud para actualizar una cita existente.
     * @param request La solicitud SOAP que contiene los datos actualizados de la cita.
     * @return La respuesta SOAP con la cita actualizada y un mensaje.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "actualizarCitaRequest")
    @ResponsePayload
    public ActualizarCitaResponse actualizarCita(@RequestPayload ActualizarCitaRequest request) {
        ActualizarCitaResponse response = new ActualizarCitaResponse();
        Long citaId = request.getCita().getId();

        if (citaId == null) {
            response.setMensaje("El ID de la cita es requerido para la actualización.");
            return response;
        }

        // Busca la cita existente por ID
        Optional<CitaEntity> existingCitaOptional = citaRepository.findById(citaId);

        if (existingCitaOptional.isPresent()) {
            CitaEntity existingCita = existingCitaOptional.get();
            // Copia las propiedades de la solicitud a la entidad existente, excluyendo el ID
            BeanUtils.copyProperties(request.getCita(), existingCita, "id"); 

            // Convierte XMLGregorianCalendar a LocalDate para la entidad JPA
            if (request.getCita().getFechaCita() != null) {
                existingCita.setFechaCita(LocalDate.of(
                        request.getCita().getFechaCita().getYear(),
                        request.getCita().getFechaCita().getMonth(),
                        request.getCita().getFechaCita().getDay()
                ));
            }

            // Guarda la entidad actualizada en la base de datos
            CitaEntity updatedCita = citaRepository.save(existingCita);

            Cita citaWs = new Cita();
            BeanUtils.copyProperties(updatedCita, citaWs);
            // Convierte LocalDate a XMLGregorianCalendar para la respuesta SOAP
            if (updatedCita.getFechaCita() != null) {
                try {
                    citaWs.setFechaCita(DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(updatedCita.getFechaCita().toString()));
                } catch (DatatypeConfigurationException e) {
                    e.printStackTrace();
                    response.setMensaje("Error al procesar la fecha de la cita.");
                    return response;
                }
            }
            response.setCita(citaWs);
            response.setMensaje("Cita actualizada exitosamente.");
        } else {
            response.setMensaje("Cita no encontrada para el ID: " + citaId);
        }
        return response;
    }

    /**
     * Maneja la solicitud para eliminar una cita por su ID.
     * @param request La solicitud SOAP que contiene el ID de la cita a eliminar.
     * @return La respuesta SOAP con un mensaje de éxito o no encontrada.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "eliminarCitaRequest")
    @ResponsePayload
    public EliminarCitaResponse eliminarCita(@RequestPayload EliminarCitaRequest request) {
        EliminarCitaResponse response = new EliminarCitaResponse();
        // Verifica si la cita existe antes de intentar eliminarla
        if (citaRepository.existsById(request.getId())) {
            citaRepository.deleteById(request.getId());
            response.setMensaje("Cita eliminada exitosamente.");
        } else {
            response.setMensaje("Cita no encontrada para el ID: " + request.getId());
        }
        return response;
    }

    /**
     * Maneja la solicitud para obtener todas las citas registradas.
     * @param request La solicitud SOAP (vacía).
     * @return La respuesta SOAP con una lista de todas las citas y un mensaje.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "obtenerTodasCitasRequest")
    @ResponsePayload
    public ObtenerTodasCitasResponse obtenerTodasCitas(@RequestPayload ObtenerTodasCitasRequest request) {
        ObtenerTodasCitasResponse response = new ObtenerTodasCitasResponse();
        // Obtiene todas las citas de la base de datos
        List<CitaEntity> citasEntities = citaRepository.findAll();

        if (!citasEntities.isEmpty()) {
            // Mapea la lista de entidades JPA a una lista de objetos Cita para la respuesta SOAP
            List<Cita> citasWs = citasEntities.stream().map(citaEntity -> {
                Cita citaWs = new Cita();
                BeanUtils.copyProperties(citaEntity, citaWs);
                // Convierte LocalDate a XMLGregorianCalendar para cada cita en la lista
                if (citaEntity.getFechaCita() != null) {
                    try {
                        citaWs.setFechaCita(DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(citaEntity.getFechaCita().toString()));
                    } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                        // Manejo de errores para la conversión de fecha en el stream
                    }
                }
                return citaWs;
            }).collect(Collectors.toList());
            response.getCitas().addAll(citasWs); // Añade todas las citas a la lista de la respuesta
            response.setMensaje("Lista de citas obtenida exitosamente.");
        } else {
            response.setMensaje("No hay citas registradas.");
        }
        return response;
    }
}
