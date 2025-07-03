package com.example.citas.repository;

import com.example.citas.model.CitaEntity; // Importa la entidad CitaEntity
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base de Spring Data JPA
import org.springframework.stereotype.Repository; // Anotación para marcar como componente de repositorio

@Repository // Marca esta interfaz como un componente de repositorio de Spring
public interface CitaRepository extends JpaRepository<CitaEntity, Long> {

}
