package com.cibertec.edu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.dto.TokenDto;
import com.cibertec.edu.dto.UsuarioDto;
import com.cibertec.edu.models.AuthUsuario;
import com.cibertec.edu.services.AuthUsuarioService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/v1/auth")
public class AuthUsuarioController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthUsuarioController.class);
	
	
	private final AuthUsuarioService authService;


	public AuthUsuarioController(AuthUsuarioService authService) {
		super();
		this.authService = authService;
	}
	
	@Transactional(rollbackOn = Exception.class)
	@PostMapping("/create")
	public ResponseEntity<AuthUsuario> create(@RequestBody UsuarioDto dto){
		try {
			AuthUsuario authUser = authService.create(dto);
			if(authUser==null) {
				LOGGER.warn("El usuario no fue creado con exito revise los logs");
				throw new IllegalArgumentException("Revisar el contenido del DTO");
			}
			LOGGER.info("Usuario creado con exito!: " + authUser.getUsername());
			return ResponseEntity.ok(authUser);
		} catch (Exception e) {
			LOGGER.error("Error: {}",e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto usuario){
		try {
			if(usuario == null) {
				throw new IllegalArgumentException("El usuario no es correcto, revise username y contraseña");
			}
			TokenDto token = authService.login(usuario);
			
			if(token == null){
				LOGGER.error("Login fallido: credenciales incorrectas");
				return ResponseEntity.status(401).body(null); // o .badRequest() si prefieres
			}
			
			LOGGER.info("Usuario logueado!");
			return ResponseEntity.ok(token);
			
		} catch (Exception e) {
			LOGGER.error("Error: {}", e.getMessage());
			return ResponseEntity.status(500).body(null);
		}
	}

	
	@PostMapping("/validate")
	public ResponseEntity<TokenDto> validate(@RequestParam("token") String token) {
		try {
			LOGGER.info("Iniciando validacion del token...");
			LOGGER.info("TOKEN: {}", token);
			TokenDto tokenDto = authService.validate(token);
			if(tokenDto == null) {
				LOGGER.error("Error al validar el token");
				return ResponseEntity.badRequest().build();
			}
			LOGGER.info("token valido!");
			return ResponseEntity.ok(tokenDto);
		} catch (Exception e) {
			LOGGER.error("Error: {}",e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}
	

	
}
