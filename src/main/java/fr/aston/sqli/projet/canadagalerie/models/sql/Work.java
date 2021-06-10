package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.io.Serializable;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "image")
	private String image;

	@Column(name = "date_production")
	private String dateProduction;

	@Column(name = "materiaux")
	private String materiaux;

	// FIXME @JsonIgnore A remplacer par un DTO
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "work_artist", joinColumns = {
			@JoinColumn(name = "work_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "artists_id", nullable = false) })
	private List<Artist> artists;

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
		Work other = (Work) obj;
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
		builder.append("Work [id=");
		builder.append(this.id);
		builder.append(", code=");
		builder.append(this.code);
		builder.append(", titre=");
		builder.append(this.titre);
		builder.append(", collection=");
		builder.append(this.collection);
		builder.append(", culture=");
		builder.append(this.culture);
		builder.append(", description=");
		builder.append(this.description);
		builder.append(", dimensions=");
		builder.append(this.dimensions);

		if (this.artists != null && !this.artists.isEmpty()) {
			builder.append(", artists=");
			for (Artist a : this.artists) {
				builder.append(a.getId()).append(",");
			}
			builder.delete(builder.length() - 1, builder.length());
		}

		builder.append(", image=");
		builder.append(this.image);
		builder.append(", dateProduction=");
		builder.append(this.dateProduction);
		builder.append(", materiaux=");
		builder.append(this.materiaux);
		builder.append("]");
		return builder.toString();
	}

}
