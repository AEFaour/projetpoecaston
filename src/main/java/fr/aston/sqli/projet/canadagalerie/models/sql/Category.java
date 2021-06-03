package fr.aston.sqli.projet.canadagalerie.models.sql;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Category {
	ADMIN(1, "Admin"), VISITOR(2, "Visteur"), GUIDE(3, "Guide");
	
	private final int val;
	private final String label;
	
	 @Override
	    public String toString() {
	        
	        return String.valueOf(this.val);
	    }
	
	

}
