package fr.aston.sqli.projet.canadagalerie.models.sql;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "num")
	private String num;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "zip")
	private String zip;
	
	@Column(name = "city")
	private String city;
	

	// FIXME @JsonIgnore A remplacer par un DTO
	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<Exploiter> exploiters;
	
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
		Address other = (Address) obj;
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
		builder.append("Address [id=");
		builder.append(this.id);
		builder.append(", num=");
		builder.append(this.num);
		builder.append(", street=");
		builder.append(this.street);
		builder.append(", zip=");
		builder.append(this.zip);
		builder.append(", city=");
		builder.append(this.city);
		if (this.exploiters != null && !this.exploiters.isEmpty()) {
			builder.append(", exploiters=");
			for (Exploiter exploiter : this.exploiters) {
				builder.append(exploiter.getId()).append(",");
			}
			builder.delete(builder.length() - 1, builder.length());
		}

		builder.append("]");
		return builder.toString();
	}
	
	
	

}
