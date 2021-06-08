package fr.aston.sqli.projet.canadagalerie.authentication;

import java.util.Optional;

public interface ApplicationUserDao {
	Optional<ApplicationUser> selectApplicationUserByUsername (String username);
}
