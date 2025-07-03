package com.example.citas.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean; // Para registrar servlets en Spring Boot
import org.springframework.context.ApplicationContext; // Contexto de la aplicación Spring
import org.springframework.context.annotation.Bean; // Anotación para declarar un bean de Spring
import org.springframework.context.annotation.Configuration; // Anotación para marcar la clase como configuración de Spring
import org.springframework.core.io.ClassPathResource; // Para acceder a recursos del classpath
import org.springframework.ws.config.annotation.EnableWs; // Habilita la configuración de Spring Web Services
import org.springframework.ws.config.annotation.WsConfigurerAdapter; // Clase base para configurar Spring WS
import org.springframework.ws.transport.http.MessageDispatcherServlet; // Servlet principal de Spring WS
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition; // Para generar el WSDL 1.1
import org.springframework.xml.xsd.SimpleXsdSchema; // Para cargar un esquema XSD simple
import org.springframework.xml.xsd.XsdSchema; // Interfaz para esquemas XSD

@EnableWs // Habilita la funcionalidad de servicios web SOAP en Spring Boot
@Configuration // Marca esta clase como una clase de configuración de Spring
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Registra el MessageDispatcherServlet de Spring Web Services.
     * Este servlet es el punto de entrada para todas las solicitudes SOAP.
     * Se mapea a la URL "/ws/*".
     * @param applicationContext El contexto de la aplicación Spring.
     * @return Un objeto ServletRegistrationBean configurado.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        // Permite que las ubicaciones en el WSDL sean relativas y se transformen correctamente
        servlet.setTransformWsdlLocations(true); 
        // Mapea el servlet para que escuche las solicitudes en /ws/
        return new ServletRegistrationBean<>(servlet, "/ws/*"); 
    }

    /**
     * Configura y genera la definición del WSDL (Web Services Description Language).
     * El nombre del bean ("citas") se usará como el nombre del archivo WSDL (ej. citas.wsdl).
     * @param citasSchema El esquema XSD que define la estructura de los mensajes.
     * @return Un objeto DefaultWsdl11Definition configurado.
     */
    @Bean(name = "citas") // El nombre del bean será el nombre del WSDL (ej. http://localhost:8080/ws/citas.wsdl)
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema citasSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CitasPort"); // Nombre del puerto en el WSDL
        wsdl11Definition.setLocationUri("/ws"); // URL base donde se expondrá el servicio SOAP
        wsdl11Definition.setTargetNamespace("http://www.example.com/citas"); // Namespace del XSD
        wsdl11Definition.setSchema(citasSchema); // Asocia el esquema XSD a este WSDL
        return wsdl11Definition;
    }

    /**
     * Carga el esquema XSD desde la ruta de recursos del proyecto.
     * @return Un objeto XsdSchema que representa el archivo citas.xsd.
     */
    @Bean
    public XsdSchema citasSchema() {
        // Carga el archivo citas.xsd desde src/main/resources/xsd/
        return new SimpleXsdSchema(new ClassPathResource("xsd/citas.xsd")); 
    }
}
