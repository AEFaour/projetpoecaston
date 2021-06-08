package fr.aston.sqli.projet.canadagalerie.authentication;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.dao.IExploiterRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;

@Repository("dbRepo")
public class DbUserDaoRepo implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	private final IExploiterRepository exploiterRepo;

	@Autowired
	public DbUserDaoRepo(PasswordEncoder passwordEncoder, IExploiterRepository exploiterRepo) {
		this.passwordEncoder = passwordEncoder;
		this.exploiterRepo = exploiterRepo;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		Optional<Exploiter> userOpt = exploiterRepo.findByEmail(username);
		Exploiter user = userOpt.get();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		Set<SimpleGrantedAuthority> authorities = user.getCategory().getGrantedAuthorities();
		ApplicationUser findedUser = new ApplicationUser(username, encodedPassword, authorities,
				true, true, true, true);
		return Optional.of(findedUser);

	}

}
