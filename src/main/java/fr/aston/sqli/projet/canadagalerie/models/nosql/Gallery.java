package fr.aston.sqli.projet.canadagalerie.models.nosql;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "gallery")
public class Gallery {

	@Id
	private ObjectId _id;
	@Field(value= "id")
	private String Id;
	@Field(value= "titre")
	private String titre;
	@Field(value= "collection")
	private String collection; 
	@Field(value= "culture")
	private String culture; 
	@Field(value= "description")
	private String description; 
	@Field(value= "dimensions")
	private String dimensions; 
	@Field(value= "artistes")
	private List<Artiste> artistes;
	@Field(value= "image")
	private String image; 
	@Field(value= "date_production")
	private String dateProduction; 
	@Field(value= "materiaux")
	private String materiaux;
	
}
