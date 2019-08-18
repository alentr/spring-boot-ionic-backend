package com.alexandre.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.alexandre.cursomc.security.UserSS;

public class UserService {
	/**
	 * Retorna o usuário logado
	 * @return
	 */
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
