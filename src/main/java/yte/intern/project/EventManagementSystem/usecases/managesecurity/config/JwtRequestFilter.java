package yte.intern.project.EventManagementSystem.usecases.managesecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.CustomUserDetailsManager;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Authority;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.entity.Users;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static yte.intern.project.EventManagementSystem.usecases.managesecurity.util.Constants.SECRET_KEY;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


	private final CustomUserDetailsManager userDetailsManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authentication = request.getHeader("Authorization");
		if (authentication != null && authentication.startsWith("Bearer")) {
			String jwtToken = authentication.substring(7);
			String username = JwtUtil.extractUsername(jwtToken, SECRET_KEY);

			Users userDetails = (Users) userDetailsManager.loadUserByUsername(username);

			if (SecurityContextHolder.getContext().getAuthentication() == null) {
                List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
			    for(Authority a: userDetails.getAuthorities()){
			        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(a.getAuthority()));
                }
                System.out.println(simpleGrantedAuthorityList.get(0).getAuthority());
				var token = new UsernamePasswordAuthenticationToken(userDetails, null, simpleGrantedAuthorityList);
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}

		filterChain.doFilter(request, response);
	}
}
