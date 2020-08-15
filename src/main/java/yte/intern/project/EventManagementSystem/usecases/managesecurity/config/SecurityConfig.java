package yte.intern.project.EventManagementSystem.usecases.managesecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import yte.intern.project.EventManagementSystem.usecases.managesecurity.CustomUserDetailsManager;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsManager userDetailsManager;
	private final PasswordEncoder passwordEncoder;
	private final JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll()
                .antMatchers("/events-application/**").permitAll()
                .antMatchers("/events/*/applications").hasAnyAuthority("ADMIN", "EVENT_MANAGER")
                .antMatchers(HttpMethod.GET, "/events/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/events/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/events/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/events/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin().disable()
				.logout().disable()
				.httpBasic().disable()
				.csrf().disable();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsManager);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}

}
