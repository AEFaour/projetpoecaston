package fr.aston.sqli.projet.canadagalerie.auth;

import static fr.aston.sqli.projet.canadagalerie.models.sql.Role.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import fr.aston.sqli.projet.canadagalerie.dao.IExploiterRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;

@Repository("DoMBSRS")
public class ModelBaseSecurityServiceImpl implements IModelBaseSecurityRepository {

	private final PasswordEncoder passWordEncoder;

	@Autowired
	private IExploiterRepository exploiterRepository;

	@Autowired
	public ModelBaseSecurityServiceImpl(PasswordEncoder passWordEncoder) {
		this.passWordEncoder = passWordEncoder;
	}

	@Override
	public Optional<ModelBaseSecurity> selectModelBaseSecurityByUsername(String username) {
		return getModelBaseSecurities(username).stream()
				.filter(modelBaseSecurity -> username.equals(modelBaseSecurity.getUsername())).findFirst();
	}

	public List<ModelBaseSecurity> getModelBaseSecurities(String username) {
		List<ModelBaseSecurity> modelBaseSecurities = Lists.newArrayList();

		Optional<Exploiter> exploiter = exploiterRepository.findByEmail(username);

		ModelBaseSecurity modelBaseSecurity = new ModelBaseSecurity(exploiter.get().getEmail(),
				exploiter.get().getPassword(),
				exploiter.get().getRole().getGrantedAuthorities(), true, true, true, true);
		
		modelBaseSecurities.add(modelBaseSecurity);

//				new ModelBaseSecurity("madame@user.com", passWordEncoder.encode("123"), ADMIN.getGrantedAuthorities(), true, true, true, true),
//				new ModelBaseSecurity("girl@user.com", passWordEncoder.encode("123"), VISITOR.getGrantedAuthorities(), true, true, true, true),
//				new ModelBaseSecurity("boy@user.com", passWordEncoder.encode("123"), GUIDE.getGrantedAuthorities(), true, true, true, true));
		return modelBaseSecurities;
	}
}
