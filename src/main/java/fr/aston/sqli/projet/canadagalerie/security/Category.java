package fr.aston.sqli.projet.canadagalerie.security;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static fr.aston.sqli.projet.canadagalerie.security.UserPermissions.*;


public enum Category {
	VISITOR(Sets.newHashSet(ARTIST_READ, ADDRESS_READ, ADDRESS_WRITE, EXPLOITER_READ, EXPLOITER_WRITE, GALLEY_READ,
			GUIDEDTOUR_READ, WORK_READ)),

	GUIDE(Sets.newHashSet(ARTIST_READ, ADDRESS_READ, ADDRESS_WRITE, EXPLOITER_READ, GALLEY_READ, GUIDEDTOUR_READ,
			GUIDEDTOUR_WRITE, WORK_READ, WORK_WRITE)),

	ADMIN(Sets.newHashSet(ARTIST_READ, ARTIST_WRITE, ADDRESS_READ, ADDRESS_WRITE, EXPLOITER_READ, EXPLOITER_WRITE, GALLEY_READ,
			GALLERY_WRITE, GUIDEDTOUR_READ, GUIDEDTOUR_WRITE, WORK_READ, WORK_WRITE));

	private final Set<UserPermissions> permissions;

	private Category(Set<UserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<UserPermissions> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
