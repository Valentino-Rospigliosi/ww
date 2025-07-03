package com.cibertec.edu.ws;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "RegistrarClienteRequest", namespace = "http://cibertec.edu/clientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrarClienteRequest {

    @XmlElement(required = true)
    private String nombre;

    @XmlElement(required = true)
    private String apellido;

    @XmlElement(required = true)
    private String fechaNacimiento;

    @XmlElement(required = true)
    private String telefono;

    @XmlElement(required = true)
    private String email;

    @XmlElement(required = true)
    private String direccion;

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
