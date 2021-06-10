package fr.aston.sqli.projet.canadagalerie.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthentFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LOG = LogManager.getLogger();
	private final AuthenticationManager authenticationManager;
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	public JwtUsernameAndPasswordAuthentFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig,
			SecretKey secretKey) {
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UsernameAndPasswordAuthentRequest authenticationRequest = new ObjectMapper()
					.readValue(request.getInputStream(), UsernameAndPasswordAuthentRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			JwtUsernameAndPasswordAuthentFilter.LOG.debug(
					"JwtUsernameAndPasswordAuthentFilter.attemptAuthentication - authentication ok, authentication => {}",
					authentication);
			return authenticate;
		} catch (IOException e) {
			JwtUsernameAndPasswordAuthentFilter.LOG.error(
					"JwtUsernameAndPasswordAuthentFilter.attemptAuthentication - authentication failled, please verify your username and password");
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		JwtUsernameAndPasswordAuthentFilter.LOG.info(
				"JwtUsernameAndPasswordAuthentFilter.successfulAuthentication - authentication oki, token building => finished if no errors!");
		String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
				.signWith(secretKey).compact();
		JwtUsernameAndPasswordAuthentFilter.LOG.debug(
				"JwtUsernameAndPasswordAuthentFilter.successfulAuthentication - authentication oki, header: {} => token: {}",
				jwtConfig.getAuthorizationHeader(), token);
		response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
	}

}
