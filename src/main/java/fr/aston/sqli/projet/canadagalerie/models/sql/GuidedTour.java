package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "guidedtour_exploiter", joinColumns = {
			@JoinColumn(name = "guidedtour_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "exploiters_id", nullable = false) })
	private List<Exploiter> exploiters;
	
	@JsonbDateFormat(locale = "fr_FR", value = "dd-MM-yyyy")
	@Column(name = "date_tour")
	private LocalDate dateTour;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		GuidedTour other = (GuidedTour) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuidedTour [id=");
		builder.append(this.id);
		if (this.exploiters != null && !this.exploiters.isEmpty()) {
			builder.append(", exploiters=");
			for (Exploiter exploiter : this.exploiters) {
				builder.append(exploiter.getId()).append(",");
			}
			builder.delete(builder.length() - 1, builder.length());
		}

		builder.append(", dateTour=");
		builder.append(this.dateTour);
		builder.append("]");
		return builder.toString();
	}
}
