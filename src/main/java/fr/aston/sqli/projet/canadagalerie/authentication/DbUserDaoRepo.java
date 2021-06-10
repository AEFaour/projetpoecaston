package fr.aston.sqli.projet.canadagalerie.authentication;

import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.dao.IExploiterRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;

@Repository("dbRepo")
public class DbUserDaoRepo implements ApplicationUserDao {

	private static final Logger LOG = LogManager.getLogger();
	private final IExploiterRepository exploiterRepo;

	@Autowired
	public DbUserDaoRepo(IExploiterRepository exploiterRepo) {
		this.exploiterRepo = exploiterRepo;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {

		Optional<Exploiter> userOpt = exploiterRepo.findByEmail(username);
		Exploiter user = userOpt.get();
		String encodedPassword = user.getPassword();
		Set<SimpleGrantedAuthority> authorities = user.getCategory().getGrantedAuthorities();
		ApplicationUser findedUser = new ApplicationUser(username, encodedPassword, authorities, true, true, true,
				true);
		DbUserDaoRepo.LOG.info("DbUserDaoRepo.selectApplicationUserByUsername({}) ==> finded user = {} {}", username,
				exploiterRepo.findByEmail(username).get().getLastName(),
				exploiterRepo.findByEmail(username).get().getFirstName());
		return Optional.of(findedUser);

	}

}
