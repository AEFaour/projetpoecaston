package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name= "work")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Work implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "collection")
	private String collection; 
	
	@Column(name = "culture")
	private String culture; 
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "dimensions")
	private String dimensions; 
	
	@OneToMany
	private List<Artist> artists;


	@Column(name = "image")
	private String image; 
	
	@Column(name = "date_production")
	private String dateProduction; 
	
	@Column(name = "materiaux")
	private String materiaux;
}
