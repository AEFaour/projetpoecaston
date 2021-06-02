package fr.aston.sqli.projet.canadagalerie.models.sql;

public enum Category {
	ADMIN, VISITOR, GUIDE;
	
	 @Override
	    public String toString() {
	        switch (this){
	            case ADMIN:
	                return "Admin";
	            case VISITOR:
	                return "Visteur";
	            case GUIDE:
	                return "Guide";
	          
	        }

	        return "";
	    }
	
	

}
