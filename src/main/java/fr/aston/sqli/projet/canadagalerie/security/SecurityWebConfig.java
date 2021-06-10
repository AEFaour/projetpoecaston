package fr.aston.sqli.projet.canadagalerie.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import fr.aston.sqli.projet.canadagalerie.auth.ModelBaseSecurityService;
import fr.aston.sqli.projet.canadagalerie.jwt.JwtConfig;
import fr.aston.sqli.projet.canadagalerie.jwt.JwtTokenVerifier;
import fr.aston.sqli.projet.canadagalerie.jwt.JwtUsernamePwdAuthFilter;

import static fr.aston.sqli.projet.canadagalerie.models.sql.Role.*;


import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passWordEncoder;
	private final ModelBaseSecurityService modelBaseSecurityService;
	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;

   
    @Autowired
	public SecurityWebConfig(PasswordEncoder passWordEncoder, ModelBaseSecurityService modelBaseSecurityService,
			SecretKey secretKey, JwtConfig jwtConfig) {
		super();
		this.passWordEncoder = passWordEncoder;
		this.modelBaseSecurityService = modelBaseSecurityService;
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		         .csrf().disable()
		         .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
		         .addFilter(new JwtUsernamePwdAuthFilter(authenticationManager(), jwtConfig, secretKey))
		         .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig),JwtUsernamePwdAuthFilter.class)
		         .authorizeRequests()
		         .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
		         //.antMatchers("/login").permitAll() 
		        // .antMatchers("/api/gallery", "/api/gallery/*").permitAll()
		         .anyRequest()
		         .authenticated()
		         .and();
		         
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider  = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passWordEncoder);
		daoAuthenticationProvider.setUserDetailsService(modelBaseSecurityService);
		
		return daoAuthenticationProvider;
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*");
//			}
//		};
//	}
	

}
