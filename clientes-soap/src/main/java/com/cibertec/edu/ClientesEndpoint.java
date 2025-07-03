package com.cibertec.edu;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cibertec.edu.ws.RegistrarClienteRequest;
import com.cibertec.edu.ws.RegistrarClienteResponse;

@Endpoint
public class ClientesEndpoint {

    private static final String NAMESPACE_URI = "http://cibertec.edu/clientes";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegistrarClienteRequest")
    @ResponsePayload
    public RegistrarClienteResponse registrarCliente(@RequestPayload RegistrarClienteRequest request) {
        RegistrarClienteResponse response = new RegistrarClienteResponse();

        String mensaje = String.format(
            "✅ Cliente registrado: %s %s, teléfono %s, email %s, dirección %s, nacido en %s",
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
