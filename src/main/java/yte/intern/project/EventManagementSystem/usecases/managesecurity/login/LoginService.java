package yte.intern.project.EventManagementSystem.usecases.managesecurity.login;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import yte.intern.project.EventManagementSystem.common.exceptionhandling.CustomException;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Authority;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;

import static yte.intern.project.EventManagementSystem.usecases.managesecurity.util.Constants.SECRET_KEY;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final DaoAuthenticationProvider authenticationProvider;

	public LoginResponse authenticate(final LoginRequest loginRequest) {

		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
			if(user.isAuthenticated()) {
                String token = JwtUtil.generateToken(user, SECRET_KEY, 15);
                List<String> authorities = new ArrayList<String>();
                for (GrantedAuthority a : user.getAuthorities()) {
                    authorities.add(a.getAuthority());
                }
                return new LoginResponse(token, authorities);
            }
			else{
                throw new CustomException("Kimlik doğrulanamadı");
            }
		} catch (AuthenticationException e) {
            throw new CustomException("Böyle bir kullanıcı bulunamadı");
		}

	}
}
