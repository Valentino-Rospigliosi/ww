package com.cibertec.edu;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cibertec.edu.ws.RegistrarCitaRequest;
import com.cibertec.edu.ws.RegistrarCitaResponse;

@Endpoint
public class CitasEndpoint {

    private static final String NAMESPACE_URI = "http://cibertec.edu/citas";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegistrarCitaRequest")
    @ResponsePayload
    public RegistrarCitaResponse registrarCita(@RequestPayload RegistrarCitaRequest request) {
        RegistrarCitaResponse response = new RegistrarCitaResponse();

        String mensaje = "✅ Cita registrada para " + request.getNombrePaciente() + " " +
                         request.getApellidoPaciente() + " con el Dr. " + request.getMedico() +
                         " en " + request.getEspecialidad() + " el " + request.getFecha();

        response.setResultado(mensaje);
        return response;
    }
}
