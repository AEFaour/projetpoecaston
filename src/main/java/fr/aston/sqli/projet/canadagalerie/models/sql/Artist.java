package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "artist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom")
	private String nom;

	// FIXME @JsonIgnore A remplacer par un DTO
	@JsonIgnore
	@ManyToMany(mappedBy = "artists")
	private List<Work> works;

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
		Artist other = (Artist) obj;
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
		builder.append("Artist [id=");
		builder.append(this.id);
		builder.append(", nom=");
		builder.append(this.nom);
		if (this.works != null && !this.works.isEmpty()) {
			builder.append(", works=");
			for (Work w : this.works) {
				builder.append(w.getId()).append(",");
			}
			builder.delete(builder.length() - 1, builder.length());
		}
		builder.append("]");
		return builder.toString();
	}

}