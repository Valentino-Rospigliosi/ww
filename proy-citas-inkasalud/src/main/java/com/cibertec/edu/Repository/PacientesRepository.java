package com.cibertec.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.edu.Entity.Pacientes;

import java.util.List;


public interface PacientesRepository extends JpaRepository<Pacientes, Integer> {
    @Query("SELECT p FROM Pacientes p WHERE " +
            "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) OR " +
            "LOWER(p.apellido) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))")
    List<Pacientes> buscarPaciente(@Param("textoBusqueda") String textoBusqueda);


    //Optional<Pacientes> findById(Integer id);
}
