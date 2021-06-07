package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.google.common.collect.Sets;

import fr.aston.sqli.projet.canadagalerie.security.ExploiterPermission;
import static fr.aston.sqli.projet.canadagalerie.security.ExploiterPermission.*;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN(
			1, "Admin", 
			Sets.newHashSet(
					EXPLOITER_READ, EXPLOITER_WRITE, GUIDESTOUR_READ, GUIDESTOUR_WRITE)),
	GUIDE(2, "Guide", Sets.newHashSet(
			EXPLOITER_READ, GUIDESTOUR_READ)),
	VISITOR(3, "Visteur", Sets.newHashSet());
	
	
	private final int val;
	
	private final String label;
	
	private final Set<ExploiterPermission> permissions;
	
	
	
	 @Override
	    public String toString() {
	        
	        return String.valueOf(this.val);
	    }
	
	

}
