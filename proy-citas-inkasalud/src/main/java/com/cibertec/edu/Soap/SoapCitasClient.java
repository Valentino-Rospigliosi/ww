package com.cibertec.edu.Soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.citas.ws.ObtenerTodasCitasRequest;
import com.example.citas.ws.ObtenerTodasCitasResponse;

public class SoapCitasClient extends WebServiceGatewaySupport {

    public ObtenerTodasCitasResponse obtenerTodasCitas() {
        ObtenerTodasCitasRequest request = new ObtenerTodasCitasRequest();
        return (ObtenerTodasCitasResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }
}
