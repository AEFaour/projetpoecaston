package fr.aston.sqli.projet.canadagalerie.security;

public enum UserPermissions {
	ARTIST_READ("artist:read"),
	ARTIST_WRITE("artist:write"),
	ADDRESS_READ("address:read"),
	ADDRESS_WRITE("address:write"),
	EXPLOITER_READ("exploiter:read"),
	EXPLOITER_WRITE("exploiter:write"),
	GALLEY_READ("gallery:read"),
	GALLERY_WRITE("gallery:write"),
	GUIDEDTOUR_READ("guidedtour:read"),
	GUIDEDTOUR_WRITE("guidedtour:write"),
	WORK_READ("work:read"),
	WORK_WRITE("work:write");
	
	private final String permission;

	private UserPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}

