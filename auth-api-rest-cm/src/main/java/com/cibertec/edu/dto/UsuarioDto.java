package com.cibertec.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UsuarioDto {

	private String nickname;
	private String password;
	
	public UsuarioDto(String nickname, String password) {
		super();
		this.nickname = nickname;
		this.password = password;
	}

	public UsuarioDto() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
