package com.cibertec.edu.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.edu.models.AuthUsuario;

@Repository
public interface IAuthUsuarioRepository extends CrudRepository<AuthUsuario, Integer> {
	
	Optional<AuthUsuario> findByUsername(String username);
}
