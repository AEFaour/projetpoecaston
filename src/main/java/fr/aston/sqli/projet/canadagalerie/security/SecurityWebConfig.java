package fr.aston.sqli.projet.canadagalerie.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static fr.aston.sqli.projet.canadagalerie.models.sql.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passWordEncoder;

	@Autowired
	public SecurityWebConfig(PasswordEncoder passWordEncoder) {
		super();
		this.passWordEncoder = passWordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		         .antMatchers("/api/gallery", "/api/gallery/*").permitAll()
		         .antMatchers("/api/**").hasRole(ADMIN.name())
		         .anyRequest()
		         .authenticated()
		         .and()
		         .httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails madame = User.builder()
				.username("madame@user.com")
				.password(passWordEncoder.encode("123"))
				.roles(ADMIN.name())
				.build();

		UserDetails girl = User.builder()
				.username("girl@user.com")
				.password(passWordEncoder.encode("123"))
				.roles(VISITOR.name())
				.build();

		UserDetails boy = User.builder()
				.username("boy@user.com")
				.password(passWordEncoder.encode("123"))
				.roles(GUIDE.name())
				.build();

		return new InMemoryUserDetailsManager(madame, girl, boy);
	}

}
