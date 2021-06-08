package fr.aston.sqli.projet.canadagalerie.security;

import javax.crypto.SecretKey;

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

import fr.aston.sqli.projet.canadagalerie.authentication.ApplicationUserService;
import fr.aston.sqli.projet.canadagalerie.jwt.JwtConfig;
import fr.aston.sqli.projet.canadagalerie.jwt.JwtTokenVerifierFilter;
import fr.aston.sqli.projet.canadagalerie.jwt.JwtUsernameAndPasswordAuthentFilter;
import static fr.aston.sqli.projet.canadagalerie.security.Category.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringBootSrcurityConfig extends WebSecurityConfigurerAdapter {
	
		private final PasswordEncoder passwordEncoder;
	    private final ApplicationUserService applicationUserService;
	    private final JwtConfig jwtConfig;
		private final SecretKey secretKey;

	    @Autowired
	    public SpringBootSrcurityConfig(PasswordEncoder passwordEncoder,
	    		ApplicationUserService applicationUserService,
				JwtConfig jwtConfig,
				SecretKey secretKey) {
	    	
			this.passwordEncoder = passwordEncoder;
			this.applicationUserService = applicationUserService;
			this.jwtConfig = jwtConfig;
			this.secretKey = secretKey;
		}

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .csrf().disable()
	                .sessionManagement().
	                	sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .addFilter(new JwtUsernameAndPasswordAuthentFilter(authenticationManager(), jwtConfig, secretKey))
	                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig, secretKey), JwtUsernameAndPasswordAuthentFilter.class)
	                .authorizeRequests()
	                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
	                .anyRequest()
	                .authenticated();
	             
	    }


		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(daoAuthenticationProvider());
	    }

	    @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setPasswordEncoder(passwordEncoder);
	        provider.setUserDetailsService(applicationUserService);
	        return provider;
	    }



    }
