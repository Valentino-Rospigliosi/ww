package com.cibertec.edu.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.edu.dto.TokenDto;
import com.cibertec.edu.dto.UsuarioDto;
import com.cibertec.edu.models.AuthUsuario;
import com.cibertec.edu.repositories.IAuthUsuarioRepository;
import com.cibertec.edu.security.JwtProvider;

@Service
public class AuthUsuarioService {

	private final IAuthUsuarioRepository authRepository;
	private final PasswordEncoder encoder;
	private final JwtProvider jwtProvider;
	public AuthUsuarioService(IAuthUsuarioRepository authRepository, PasswordEncoder encoder, JwtProvider jwtProvider) {
		super();
		this.authRepository = authRepository;
		this.encoder = encoder;
		this.jwtProvider = jwtProvider;
	}
	
	public AuthUsuario create(UsuarioDto dto) {
		Optional<AuthUsuario> usuario = this.authRepository.findByUsername(dto.getNickname());
		if(usuario.isPresent()) {
			return null;
		}
		String password = encoder.encode(dto.getPassword());
		AuthUsuario user = AuthUsuario.Builder()
				.username(dto.getNickname())
				.password(password)
				.build();
		return authRepository.save(user);
	}
	
	public TokenDto login(UsuarioDto dto) {
	    Optional<AuthUsuario> usuario = authRepository.findByUsername(dto.getNickname());
	    if(!usuario.isPresent()) {
	        return null;
	    }
	    if(encoder.matches(dto.getPassword(), usuario.get().getPassword())) {
	        String token = jwtProvider.createToken(usuario.get());
	        System.out.println("✅ TOKEN GENERADO: " + token);
	        return new TokenDto(token);
	    }
	    return null;
	}

	
	public TokenDto validate(String token) {
		if(!jwtProvider.validate(token)) {
			return null;
		}
		String username = jwtProvider.getUserNameFromToken(token);
		if(!authRepository.findByUsername(username).isPresent()) {
			return null;
		}
		return new TokenDto(token);
	}
}
