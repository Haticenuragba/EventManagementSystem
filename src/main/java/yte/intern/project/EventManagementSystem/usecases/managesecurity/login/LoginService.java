package yte.intern.project.EventManagementSystem.usecases.managesecurity.login;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.util.JwtUtil;

import static yte.intern.project.EventManagementSystem.usecases.managesecurity.util.Constants.SECRET_KEY;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final DaoAuthenticationProvider authenticationProvider;

	public LoginResponse authenticate(final LoginRequest loginRequest) {

		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
			String token = JwtUtil.generateToken(user, SECRET_KEY, 15);
			return new LoginResponse(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		return null;
	}
}
