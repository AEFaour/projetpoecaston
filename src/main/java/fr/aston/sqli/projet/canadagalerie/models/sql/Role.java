package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.google.common.collect.Sets;

import fr.aston.sqli.projet.canadagalerie.security.ExploiterPermission;
import static fr.aston.sqli.projet.canadagalerie.security.ExploiterPermission.*;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN(
			1,
			"Admin", 
			Sets.newHashSet(
					ARTIST_READ, ARTIST_WRITE,
					ADDRESS_READ, ADDRESS_WRITE,
					EXPLOITER_READ, EXPLOITER_WRITE,
					GALLEY_READ, 
					GUIDEDTOUR_READ, GUIDEDTOUR_WRITE,
					WORK_READ, WORK_WRITE
					)
			),
	GUIDE(
			2,
			"Guide",
			Sets.newHashSet(
					ARTIST_READ,
					ADDRESS_READ, ADDRESS_WRITE,
					EXPLOITER_READ, 
					GALLEY_READ,
					GUIDEDTOUR_READ, GUIDEDTOUR_WRITE,
					WORK_READ, WORK_WRITE
			        )
			),
	VISITOR(
			3,
			"Visteur",
			Sets.newHashSet(
					ARTIST_READ, 
					ADDRESS_READ, ADDRESS_WRITE,
					EXPLOITER_READ, EXPLOITER_WRITE,
					GALLEY_READ, 
					GUIDEDTOUR_READ,
					WORK_READ, WORK_WRITE
					)
			);
	
	private final int val;
	
	private final String label;
	
	private final Set<ExploiterPermission> permissions;
	
	
	
	 @Override
	    public String toString() {
	        
	        return String.valueOf(this.val);
	    }
	
	 public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
	        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
	                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	                .collect(Collectors.toSet());
	        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	        return permissions;
	    }
	

}
