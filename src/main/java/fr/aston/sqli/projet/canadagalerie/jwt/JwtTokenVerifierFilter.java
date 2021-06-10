package fr.aston.sqli.projet.canadagalerie.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {
	
	private static final Logger LOG = LogManager.getLogger();	
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	public JwtTokenVerifierFilter(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
		
		JwtTokenVerifierFilter.LOG.debug("JwtAuthorizationFilter.doFilterInternal - JWT token is {}",
				authorizationHeader);
		
		if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix()))
		{
			JwtTokenVerifierFilter.LOG.warn("JwtTokenVerifierFilter.doFilterInternal - {} - JWT token is Empty");
			filterChain.doFilter(request, response);
			return;
		}
		String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
		try {
			JwtTokenVerifierFilter.LOG.info("Trying to get authentication with the token gotten from the response");		
			Jws<Claims> claimsJws = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token);
			
			Claims body = claimsJws.getBody();
			String username = body.getSubject();
			
			var authorities = (List<Map<String, String>>) body.get("authorities");
			
			Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
				.map(m -> new SimpleGrantedAuthority(m.get("authority")))
				.collect(Collectors.toSet());
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					username, 
					null,
					simpleGrantedAuthorities);			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			JwtTokenVerifierFilter.LOG.debug("JwtAuthorizationFilter.doFilterInternal - JWT token is ok {}, authentication => {}",
					token, authentication);
		} catch (JwtException e) {
			JwtTokenVerifierFilter.LOG.error("JwtAuthorizationFilter.doFilterInternal - can't trust the following givent JWT token {}",
					token);
			throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
		}
		filterChain.doFilter(request, response);
	}

}
