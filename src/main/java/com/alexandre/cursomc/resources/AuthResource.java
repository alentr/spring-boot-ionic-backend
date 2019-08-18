package com.alexandre.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.cursomc.security.JWTUtil;
import com.alexandre.cursomc.security.UserSS;
import com.alexandre.cursomc.services.UserService;

@RestController
@RequestMapping(value = "Auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;

	/**
	 * Atualiza o token do usuário
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		
		String token = jwtUtil.generateToken(user.getUsername());
		
		response.addHeader("Authorization", "Bearer " + token);

		return ResponseEntity.noContent().build();
	}
}
