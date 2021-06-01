package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="guidedtour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuidedTour implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Exploiter> exploiters;
	
	@JsonbDateFormat(locale = "fr_FR", value = "dd-MM-yyyy")
	@Column(name = "date_tour")
	private LocalDate dateTour;

}
