package com.cibertec.edu;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cibertec.edu.ws.RegistrarPacienteRequest;
import com.cibertec.edu.ws.RegistrarPacienteResponse;

@Endpoint
public class PacientesEndpoint {

    private static final String NAMESPACE_URI = "http://cibertec.edu/pacientes";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegistrarPacienteRequest")
    @ResponsePayload
    public RegistrarPacienteResponse registrarPaciente(@RequestPayload RegistrarPacienteRequest request) {
        RegistrarPacienteResponse response = new RegistrarPacienteResponse();

        String mensaje = String.format(
            "✅ Paciente registrado: %s %s, teléfono %s, email %s, dirección %s, nacido en %s",
            request.getNombre(),
            request.getApellido(),
            request.getTelefono(),
            request.getEmail(),
            request.getDireccion(),
            request.getFechaNacimiento()
        );

        response.setResultado(mensaje);
        return response;
    }
}
