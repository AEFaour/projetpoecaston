package fr.aston.sqli.projet.canadagalerie.models.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
//@Document(collation ="artistes")
public class Artiste {

	@Field(value = "id")
	private Integer id;
	@Field(value = "nom")
	private String nom;

}
