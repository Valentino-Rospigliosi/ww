package com.cibertec.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cibertec.edu.Entity.Citas;

import java.util.List;

public interface CitasRepository extends JpaRepository<Citas, Integer> {

    @Query("SELECT c FROM Citas c WHERE c.paciente.idPaciente = :id")
    List<Citas> findByPaciente_IdPaciente(@Param("id") Integer id);

    // Buscar citas por nombre del paciente o del médico
    @Query("SELECT c FROM Citas c WHERE c.paciente.nombre LIKE %:buscar% OR c.medico.nombre LIKE %:buscar%")
    List<Citas> buscarCitas(@Param("buscar") String buscar);
}
