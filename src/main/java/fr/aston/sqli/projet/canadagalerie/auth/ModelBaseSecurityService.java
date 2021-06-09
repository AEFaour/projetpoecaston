package fr.aston.sqli.projet.canadagalerie.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ModelBaseSecurityService implements UserDetailsService {

	private final IModelBaseSecurityRepository modelBaseSecurityRepository;
	
	
	@Autowired
	public ModelBaseSecurityService(@Qualifier("DoMBSRS") IModelBaseSecurityRepository modelBaseSecurityRepository) {
		this.modelBaseSecurityRepository = modelBaseSecurityRepository;
	}
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return modelBaseSecurityRepository
	                .selectModelBaseSecurityByUsername(username)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException(String.format("Username %s not found", username))
	                );
	    }

}
