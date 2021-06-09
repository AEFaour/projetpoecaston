package fr.aston.sqli.projet.canadagalerie.auth;

import java.util.Optional;

public interface IModelBaseSecurityRepository {
	
	Optional<ModelBaseSecurity> selectModelBaseSecurityByUsername(String username);

}
